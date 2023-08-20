package com.aspire.takehome.miniaspire.loan.approval.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanApprovalService {
    @Autowired
    private LoanRepository loanRepository;

    public void approveLoan(LoanApprovalRequestDTO approvalDTO) {
        LoanEntity loan = loanRepository
                .findById(approvalDTO.getLoanId())
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if (approvalDTO.isApproved()) {
            loan.setStatus(LoanStatus.APPROVED);
        } else {
            loan.setStatus(LoanStatus.REJECTED);
        }
        loanRepository.save(loan);
    }
}

