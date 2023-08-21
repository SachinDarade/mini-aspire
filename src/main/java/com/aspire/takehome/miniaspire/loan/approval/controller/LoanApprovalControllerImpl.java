package com.aspire.takehome.miniaspire.loan.approval.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalRequestDTO;
import com.aspire.takehome.miniaspire.loan.approval.dto.LoanApprovalResponseDTO;
import com.aspire.takehome.miniaspire.loan.approval.service.LoanApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/approval")
public class LoanApprovalControllerImpl implements LoanApprovalController {

    private final LoanApprovalService loanApprovalService;

    @Override
    @PutMapping
    public ResponseEntity<LoanApprovalResponseDTO> approveLoan(@RequestBody @Valid LoanApprovalRequestDTO approvalRequestDTO) {
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

