package com.aspire.takehome.miniaspire.loan.consolidation.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserLoanConsolidationService {

    private final LoanRepository loanRepository;

    private final RepaymentRepository repaymentRepository;

    public List<LoanEntity> getUserLoans(Long userId) {
        return loanRepository.findByUserId(userId);
    }

    public List<RepaymentEntity> getUserRepayments(Long userId) {
        List<LoanEntity> userLoans = loanRepository.findByUserId(userId);
        List<Long> loanIds = userLoans
                .stream()
                .map(LoanEntity::getId)
                .collect(Collectors.toList());
        return repaymentRepository.findByLoanIdIn(loanIds);
    }

    public List<LoanEntity> getUserLoans(Long userId,
                                         List<LoanStatus> statuses) {
        return loanRepository.findByUserIdAndStatusIn(
                userId,
                statuses
        );
    }
}

