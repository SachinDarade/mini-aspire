package com.aspire.takehome.miniaspire.service;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dto.LoanRequestDTO;

public interface LoanService {
    LoanEntity createLoan(LoanRequestDTO loanRequest);
}

