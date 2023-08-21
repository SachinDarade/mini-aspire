package com.aspire.takehome.miniaspire.loan.application.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationResponseDTO;
import com.aspire.takehome.miniaspire.loan.application.service.LoanApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/application")
public class LoanApplicationControllerImpl implements LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @Override
    @PostMapping
    public ResponseEntity<LoanApplicationResponseDTO> createLoan(@Valid @RequestBody LoanApplicationRequestDTO loanRequest,
                                                                 @AuthenticationPrincipal UserDetails userDetails
    ) throws UserNotFoundException {
        LoanEntity createdLoan = loanApplicationService.createLoan(
                loanRequest,
                userDetails.getUsername()
        );
        return new ResponseEntity<>(
                new LoanApplicationResponseDTO(
                        createdLoan.getId(),
                        createdLoan.getApplicationDate()
                ),
                HttpStatus.CREATED
        );
    }
}

