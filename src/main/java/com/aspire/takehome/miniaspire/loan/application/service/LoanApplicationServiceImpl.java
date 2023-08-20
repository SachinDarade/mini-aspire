package com.aspire.takehome.miniaspire.loan.application.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.ScheduledRepaymentEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.ScheduledRepaymentRepository;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanRepository loanRepository;

    private final ScheduledRepaymentRepository repaymentRepository;

    @Override
    @Transactional
    public LoanEntity createLoan(LoanApplicationRequestDTO loanRequest) {
        LoanEntity loan = new LoanEntity(
                null,
                loanRequest.getAmountRequired(),
                0.0,
                loanRequest.getLoanTerm(),
                LocalDate.now(),
                LoanStatus.PENDING
        );
        LoanEntity savedLoan = loanRepository.save(loan);

        // Calculate repayments and save them
        calculateAndSaveRepayments(savedLoan);

        return savedLoan;
    }

    private void calculateAndSaveRepayments(LoanEntity loan) {
        int loanTerm = loan.getLoanTerm();
        LocalDate currentDate = LocalDate.now();

        for (int i = 1; i <= loanTerm; i++) {
            ScheduledRepaymentEntity repayment = new ScheduledRepaymentEntity();
            repayment.setDueDate(currentDate.plusWeeks(i));
            repayment.setAmountPaid(0.0); // Initialize amount paid to 0
            repayment.setStatus(RepaymentStatus.PENDING);
            repayment.setLoan(loan);

            repaymentRepository.save(repayment);
        }
    }
}
