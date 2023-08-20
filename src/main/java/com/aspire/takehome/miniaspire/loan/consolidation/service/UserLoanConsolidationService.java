package com.aspire.takehome.miniaspire.loan.consolidation.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;

import java.util.List;

public interface UserLoanConsolidationService {
    List<LoanEntity> getUserLoans(Long userId);

    List<RepaymentEntity> getUserRepayments(Long userId);

    List<LoanEntity> getUserLoans(
            Long userId,
            List<LoanStatus> statuses
    );
}
