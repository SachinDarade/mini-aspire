package com.aspire.takehome.miniaspire.loan.consolidation.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;

import java.util.List;

public interface AdminLoanConsolidationService {
    List<LoanEntity> getLoansWithStatuses(List<LoanStatus> statuses);

    List<LoanEntity> getLoansByUsername(String username) throws UserNotFoundException;
}
