package com.aspire.takehome.miniaspire.loan.application.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import com.aspire.takehome.miniaspire.loan.application.service.LoanApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanApplicationController {
    @Autowired
    private LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<LoanEntity> createLoan(@RequestBody LoanApplicationRequestDTO loanRequest) {
        LoanEntity createdLoan = loanApplicationService.createLoan(loanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }
}

