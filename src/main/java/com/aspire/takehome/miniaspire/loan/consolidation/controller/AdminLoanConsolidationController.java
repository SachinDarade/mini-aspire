package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.AdminLoanConsolidationService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/admin-screen")
public class AdminLoanConsolidationController {
    @Autowired
    private AdminLoanConsolidationService adminLoanConsolidationService;

    @GetMapping
    public ResponseEntity<List<LoanEntity>> getLoansWithStatuses(@RequestBody ConsolidationRequestDTO consolidationRequest) {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansWithStatuses(consolidationRequest.getStatuses());
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<LoanEntity>> getLoansByUsername(@PathVariable @NonNull String username) throws UserNotFoundException {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansByUsername(username);
        return ResponseEntity.ok(loans);
    }
}

