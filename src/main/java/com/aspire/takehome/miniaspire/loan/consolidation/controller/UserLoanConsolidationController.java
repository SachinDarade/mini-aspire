package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserLoanConsolidationController {
    @GetMapping
    ResponseEntity<ConsolidationResponseDTO> getUserLoans(@RequestParam Long userId);

    @GetMapping("/status")
    ResponseEntity<ConsolidationResponseDTO> getUserLoans(
            @RequestParam Long userId,
            @RequestBody ConsolidationRequestDTO consolidationRequest
    );

    @GetMapping("/repayments")
    ResponseEntity<List<RepaymentEntity>> getUserRepayments(@RequestParam Long userId);
}
