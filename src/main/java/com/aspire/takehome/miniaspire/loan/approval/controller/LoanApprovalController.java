package com.aspire.takehome.miniaspire.loan.approval.controller;

import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalRequestDTO;
import com.aspire.takehome.miniaspire.loan.approval.service.LoanApprovalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mini-aspire/v1/loan/approval")
public class LoanApprovalController {
    @Autowired
    private LoanApprovalService loanApprovalService;

    @PutMapping
    public ResponseEntity<Void> approveLoan(@RequestBody LoanApprovalRequestDTO approvalRequestDTO) {
        loanApprovalService.approveLoan(approvalRequestDTO);
        return ResponseEntity.ok().build();
    }
}

