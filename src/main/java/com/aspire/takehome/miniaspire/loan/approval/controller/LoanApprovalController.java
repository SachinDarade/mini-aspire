package com.aspire.takehome.miniaspire.loan.approval.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalRequestDTO;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalResponseDTO;
import com.aspire.takehome.miniaspire.loan.approval.service.LoanApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/approval")
public class LoanApprovalController {

    private final LoanApprovalService loanApprovalService;

    @PutMapping
    public ResponseEntity<LoanApprovalResponseDTO> approveLoan(@RequestBody LoanApprovalRequestDTO approvalRequestDTO) {
        LoanEntity loan = loanApprovalService.approveLoan(
                approvalRequestDTO.getLoanId(),
                approvalRequestDTO.isApproved()
        );
        return new ResponseEntity<>(
                new LoanApprovalResponseDTO(
                        loan.getId(),
                        loan.getStatus()
                ),
                HttpStatus.OK
        );
    }
}

