package com.aspire.takehome.miniaspire.loan.approval.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerApprovedLoanService {  // TODO: Use this and remove afterwards
    @Autowired
    private LoanRepository loanRepository;

//    public List<LoanApprovalResponseDTO> getApprovedLoansForUser(Long userId) {
//        List<LoanEntity> approvedLoans = loanRepository.findByUserIdAndStatus(
//                userId,
//                LoanStatus.APPROVED
//        );
//        return approvedLoans.stream()
//                .map(this::convertToApprovedLoanDTO)
//                .collect(Collectors.toList());
//    }
//
//    private LoanApprovalResponseDTO convertToApprovedLoanDTO(LoanEntity loan) {
//        return LoanApprovalResponseDTO.builder()
//                .id(loan.getId())
//                .amountRequired(loan.getAmountRequired())
//                .loanTerm(loan.getLoanTerm())
//                .applicationDate(loan.getApplicationDate())
//                .amountPaid(loan.getAmountPaid())
//                .status(loan.getStatus())
//                .build();
//    }
}

