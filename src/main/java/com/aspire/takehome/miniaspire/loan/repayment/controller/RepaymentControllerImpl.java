package com.aspire.takehome.miniaspire.loan.repayment.controller;

import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.common.exceptions.ResourceNotAuthorizedException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentResponseDTO;
import com.aspire.takehome.miniaspire.loan.repayment.service.RepaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/repayment")
public class RepaymentControllerImpl implements RepaymentController {

    private final RepaymentService repaymentService;

    private final LoanRepository loanRepository;

    @Override
    @PutMapping
    public ResponseEntity<RepaymentResponseDTO> makeRepayment(@Valid @RequestBody RepaymentRequestDTO repaymentRequest,
                                                              @AuthenticationPrincipal UserDetails userDetails) throws RepaymentAmountInvalidException {
        // Check if user is valid, that is, he is accessing his own loan only
        checkUserLoanValidity(
                repaymentRequest.getLoanId(),
                userDetails.getUsername()
        );

        // If user and loan tuple is valid then make the repayment
        LoanEntity loan = repaymentService.makeRepayment(repaymentRequest);
        return new ResponseEntity<>(
                new RepaymentResponseDTO(
                        loan,
                        "Repayment successful"
                ),
                HttpStatus.OK
        );
    }

    private void checkUserLoanValidity(Long loanId,
                                       String username) {
        LoanEntity loan = loanRepository
                .findById(loanId)
                .orElseThrow(() -> new LoanNotFoundException("Loan not found"));

        if(!username.equals(loan.getUser().getUsername())) {
            throw new ResourceNotAuthorizedException("You don't have access to this loan");
        }
    }
}

