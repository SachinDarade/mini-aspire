package com.aspire.takehome.miniaspire.loan.approval.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class LoanApprovalServiceImpl implements LoanApprovalService {

    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public LoanEntity approveLoan(Long loanId,
                                  boolean loanApproved) {
        LoanEntity loan = loanRepository
                .findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if (loanApproved) {
            loan.setStatus(LoanStatus.APPROVED);
        } else {
            loan.setStatus(LoanStatus.REJECTED);
        }
        return loanRepository.save(loan);
    }
}

