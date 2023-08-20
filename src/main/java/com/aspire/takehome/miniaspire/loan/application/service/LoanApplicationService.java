package com.aspire.takehome.miniaspire.loan.application.service;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;

public interface LoanApplicationService {
    LoanEntity createLoan(LoanApplicationRequestDTO loanRequest);
}

