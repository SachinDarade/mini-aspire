package com.aspire.takehome.miniaspire.loan.repayment.controller;

import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentResponseDTO;
import com.aspire.takehome.miniaspire.loan.repayment.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/repayment")
public class RepaymentControllerImpl implements RepaymentController {

    private final RepaymentService repaymentService;

    @Override
    @PutMapping
    public ResponseEntity<RepaymentResponseDTO> makeRepayment(@RequestBody RepaymentRequestDTO repaymentRequest) throws RepaymentAmountInvalidException {
        LoanEntity loan = repaymentService.makeRepayment(repaymentRequest);
        return new ResponseEntity<>(
                new RepaymentResponseDTO(
                        loan,
                        "Repayment successful"
                ),
                HttpStatus.OK
        );
    }
}

