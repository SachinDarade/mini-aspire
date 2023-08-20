package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.AdminLoanConsolidationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/admin-screen")
public class AdminLoanConsolidationController {

    private final AdminLoanConsolidationService adminLoanConsolidationService;

    @GetMapping
    public ResponseEntity<ConsolidationResponseDTO> getLoansWithStatuses(@RequestBody ConsolidationRequestDTO consolidationRequest) {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansWithStatuses(consolidationRequest.getStatuses());
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @GetMapping("/{username}")
    public ResponseEntity<ConsolidationResponseDTO> getLoansByUsername(@PathVariable @NonNull String username) throws UserNotFoundException {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansByUsername(username);
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }
}

