package com.aspire.takehome.miniaspire.loan.approval.service;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;

import javax.transaction.Transactional;

public interface LoanApprovalService {

    /**
     * Approve/Reject the specific loan
     * @param loanId details of loan to be approved/rejected
     * @param loanApproved whether the loan is approved or not
     * @return details of loan with new status
     */
    @Transactional
    LoanEntity approveLoan(Long loanId,
                           boolean loanApproved);
}
