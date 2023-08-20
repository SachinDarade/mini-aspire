package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.UserLoanConsolidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/user-screen")
public class UserLoanConsolidationController {
    @Autowired
    private UserLoanConsolidationService userLoanConsolidationService;

    @GetMapping
    public ResponseEntity<List<LoanEntity>> getUserLoans(@RequestParam Long userId) {
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(userId);
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/status")
    public ResponseEntity<List<LoanEntity>> getUserLoans(@RequestParam Long userId,
                                                         @RequestBody ConsolidationRequestDTO consolidationRequest) {
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(userId, consolidationRequest.getStatuses());
        return ResponseEntity.ok(loans);
    }

    @GetMapping("/repayments")
    public ResponseEntity<List<RepaymentEntity>> getUserRepayments(@RequestParam Long userId) {
        List<RepaymentEntity> repayments = userLoanConsolidationService.getUserRepayments(userId);
        return ResponseEntity.ok(repayments);
    }
}

