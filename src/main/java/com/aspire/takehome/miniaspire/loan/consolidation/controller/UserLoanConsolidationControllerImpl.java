package com.aspire.takehome.miniaspire.loan.consolidation.controller;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationRequestDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.dto.ConsolidationResponseDTO;
import com.aspire.takehome.miniaspire.loan.consolidation.service.UserLoanConsolidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("mini-aspire/v1/loan/consolidation/user-screen")
public class UserLoanConsolidationControllerImpl implements UserLoanConsolidationController {

    private final UserLoanConsolidationService userLoanConsolidationService;

    private final UserDao userDao;

    // Note in the below 3 methods, I haven't used the best practices for user resource authorization
    // This could be done with Role based authorization using JWT and @RolesAllowed annotation
    // However, due to time constraint, I was not able to do that.
    @Override
    @GetMapping
    public ResponseEntity<ConsolidationResponseDTO> getUserLoans(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        UserEntity user = userDao.fetchByUsername(userDetails.getUsername());
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(user.getId());
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping("/status")
    public ResponseEntity<ConsolidationResponseDTO> getUserLoans(@AuthenticationPrincipal UserDetails userDetails,
                                                                 @Valid @RequestBody ConsolidationRequestDTO consolidationRequest) throws UserNotFoundException {
        UserEntity user = userDao.fetchByUsername(userDetails.getUsername());
        List<LoanEntity> loans = userLoanConsolidationService.getUserLoans(user.getId(), consolidationRequest.getStatuses());
        return new ResponseEntity<>(
                new ConsolidationResponseDTO(loans),
                HttpStatus.OK
        );
    }

    @Override
    @GetMapping("/repayments")
    public ResponseEntity<List<RepaymentEntity>> getUserRepayments(@AuthenticationPrincipal UserDetails userDetails) throws UserNotFoundException {
        UserEntity user = userDao.fetchByUsername(userDetails.getUsername());
        List<RepaymentEntity> repayments = userLoanConsolidationService.getUserRepayments(user.getId());
        return ResponseEntity.ok(repayments);
    }
}

