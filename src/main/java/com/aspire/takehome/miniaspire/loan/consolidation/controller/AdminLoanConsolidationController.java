package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface AdminLoanConsolidationController {

    /**
     * Get all loans with the statuses given
     * @param consolidationRequest status of loans
     * @return list og all loans with given statuses
     */
    @GetMapping
    ResponseEntity<ConsolidationResponseDTO> getLoansWithStatuses(@RequestBody ConsolidationRequestDTO consolidationRequest);

    /**
     * Get loans of given user
     * @param username username of the user
     * @return loan details of the user
     * @throws UserNotFoundException if user is not found
     */
    @GetMapping("/{username}")
    ResponseEntity<ConsolidationResponseDTO> getLoansByUsername(@PathVariable @NonNull String username) throws UserNotFoundException;
}
