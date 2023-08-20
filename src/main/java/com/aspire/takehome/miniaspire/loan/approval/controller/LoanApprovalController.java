package com.aspire.takehome.miniaspire.loan.approval.controller;

import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalRequestDTO;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoanApprovalController {

    /**
     * Approve the specific loan
     * @param approvalRequestDTO details of loan to be approved/rejected
     * @return details of loan and new status
     */
    @PutMapping
    ResponseEntity<LoanApprovalResponseDTO> approveLoan(@RequestBody LoanApprovalRequestDTO approvalRequestDTO);

}
