package com.aspire.takehome.miniaspire.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dto.LoanRequestDTO;
import com.aspire.takehome.miniaspire.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<LoanEntity> createLoan(@RequestBody LoanRequestDTO loanRequest) {
        LoanEntity createdLoan = loanService.createLoan(loanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLoan);
    }
}

