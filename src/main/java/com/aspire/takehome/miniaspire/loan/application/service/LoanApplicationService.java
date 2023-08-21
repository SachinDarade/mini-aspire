package com.aspire.takehome.miniaspire.loan.application.service;

import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;

public interface LoanApplicationService {

    /**
     * Creates new loan for the user
     * @param loanRequest Request of new loan with amount and term
     * @param userName Logged in user username
     * @return created loan details
     * @throws UserNotFoundException if the user logged is not found
     */
    LoanEntity createLoan(LoanApplicationRequestDTO loanRequest,
                          String userName) throws UserNotFoundException;
}

