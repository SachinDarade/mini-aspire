package com.aspire.takehome.miniaspire.loan.application.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface LoanApplicationController {

    /**
     * Creates new loan for the logged-in user
     * @param loanRequest Request of new loan with amount and term
     * @param userDetails Logged in user details
     * @return created loan details
     * @throws UserNotFoundException if the user logged is not found
     */
    @PostMapping
    ResponseEntity<LoanApplicationResponseDTO> createLoan(@RequestBody LoanApplicationRequestDTO loanRequest,
                                                          @AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException;
}
