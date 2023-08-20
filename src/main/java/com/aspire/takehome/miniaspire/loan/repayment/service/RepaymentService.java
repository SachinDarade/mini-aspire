package com.aspire.takehome.miniaspire.loan.repayment.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAlreadyPaidException;
import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class RepaymentService {

    private final RepaymentRepository repaymentRepository;

    private final LoanRepository loanRepository;

    @Transactional
    public LoanEntity makeRepayment(RepaymentRequestDTO repaymentRequest) throws RepaymentAmountInvalidException {

        // Step 1: Get concerned loan
        LoanEntity loan = loanRepository
                .findById(repaymentRequest.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        // Step 2: Check repayment amount is valid
        verifyRepayAmtValid(
                loan,
                repaymentRequest.getAmount()
        );

        // Step 3: Find Repayment schedule for the repayment
        RepaymentEntity scheduledRepayment = findRepaymentSchedToRepay(repaymentRequest.getLoanId());

        // Step 4: Repay
        // Step 4.1: Update scheduled repayment status
        scheduledRepayment.setStatus(RepaymentStatus.PAID);
        scheduledRepayment.setAmountPaid(scheduledRepayment.getAmountPaid() + repaymentRequest.getAmount());
        repaymentRepository.save(scheduledRepayment);

        // Step 4.2: Update total amount paid till now
        loan.setAmountPaid(loan.getAmountPaid() + repaymentRequest.getAmount());
        loan = loanRepository.save(loan);

        // Step 5: Check if all repayments are paid for the loan
        updateLoanStatusIfAllRepaymentsPaid(scheduledRepayment.getLoan());

        return loan;

    }

    private RepaymentEntity findRepaymentSchedToRepay(Long loanId) {
        return repaymentRepository
                .findFirstByLoanIdAndStatusOrderByDueDateAsc(
                        loanId,
                        RepaymentStatus.PENDING
                )
                .orElseThrow(() -> new RepaymentAlreadyPaidException("Repayment already paid"));
    }

    private void verifyRepayAmtValid(LoanEntity loan,
                                     Double repayAmountRcvd) throws RepaymentAmountInvalidException {
        if(repayAmountRcvd <=0 ) {
            throw new RepaymentAmountInvalidException("Repayment amount is non positive");
        }

        double remainingRepaymentAmount = loan.getAmountRequired() - loan.getAmountPaid();
        double generalMonthlyInstallments = loan.getAmountRequired()/loan.getLoanTerm();
        double minInstallment = Double.min(
                generalMonthlyInstallments,
                remainingRepaymentAmount
        );
        if(minInstallment > repayAmountRcvd) {
            throw new RepaymentAmountInvalidException("Repayment amount less than this month's installment");
        }
    }

    private void updateLoanStatusIfAllRepaymentsPaid(LoanEntity loan) {
        boolean repaymentRemaining = repaymentRepository.existsByLoanAndStatus(loan, RepaymentStatus.PENDING);
        if (!repaymentRemaining) {
            loan.setStatus(LoanStatus.PAID);
            loanRepository.save(loan);
        }
    }
}

