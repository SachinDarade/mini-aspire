package com.aspire.takehome.miniaspire.loan.repayment.controller;

import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import com.aspire.takehome.miniaspire.loan.repayment.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/repayment")
public class RepaymentController {

    private final RepaymentService repaymentService;

    @PostMapping
    public ResponseEntity<Void> makeRepayment(@RequestBody RepaymentRequestDTO repaymentRequest) throws RepaymentAmountInvalidException {
        repaymentService.makeRepayment(repaymentRequest);
        return ResponseEntity.ok().build();
    }
}

