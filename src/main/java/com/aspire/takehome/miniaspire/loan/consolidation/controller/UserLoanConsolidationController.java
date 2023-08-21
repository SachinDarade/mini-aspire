package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserLoanConsolidationController {
    @GetMapping
    ResponseEntity<ConsolidationResponseDTO> getUserLoans(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException;

    @GetMapping("/status")
    ResponseEntity<ConsolidationResponseDTO> getUserLoans(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestBody ConsolidationRequestDTO consolidationRequest
    ) throws UserNotFoundException;

    @GetMapping("/repayments")
    ResponseEntity<List<RepaymentEntity>> getUserRepayments(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException;
}
