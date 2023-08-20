package com.aspire.takehome.miniaspire.loan.repayment.controller;

import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RepaymentController {
    @PutMapping
    ResponseEntity<RepaymentResponseDTO> makeRepayment(@RequestBody RepaymentRequestDTO repaymentRequest) throws RepaymentAmountInvalidException;
}
