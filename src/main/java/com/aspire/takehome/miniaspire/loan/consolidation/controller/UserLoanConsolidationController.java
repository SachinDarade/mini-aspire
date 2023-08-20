package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.UserLoanConsolidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/user-screen")
public class UserLoanConsolidationController {

    private final UserLoanConsolidationService userLoanConsolidationService;

    @GetMapping
    public ResponseEntity<ConsolidationResponseDTO> getUserLoans(@RequestParam Long userId) {
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(userId);
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @GetMapping("/status")
    public ResponseEntity<ConsolidationResponseDTO> getUserLoans(@RequestParam Long userId,
                                                         @RequestBody ConsolidationRequestDTO consolidationRequest) {
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(userId, consolidationRequest.getStatuses());
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @GetMapping("/repayments")
    public ResponseEntity<List<RepaymentEntity>> getUserRepayments(@RequestParam Long userId) {
        List<RepaymentEntity> repayments = userLoanConsolidationService.getUserRepayments(userId);
        return ResponseEntity.ok(repayments);
    }
}

