package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.AdminLoanConsolidationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author sachin
 * Consolidated view of loans for admin
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/admin-screen")
public class AdminLoanConsolidationControllerImpl implements AdminLoanConsolidationController {

    private final AdminLoanConsolidationService adminLoanConsolidationService;

    @Override
    @GetMapping
    public ResponseEntity<ConsolidationResponseDTO> getLoansWithStatuses(@Valid @RequestBody ConsolidationRequestDTO consolidationRequest) {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansWithStatuses(consolidationRequest.getStatuses());
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping("/{username}")
    public ResponseEntity<ConsolidationResponseDTO> getLoansByUsername(@Valid @PathVariable @NonNull String username) throws UserNotFoundException {
        List<LoanEntity> loans = adminLoanConsolidationService.getLoansByUsername(username);
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }
}

