package com.aspire.takehome.miniaspire.loan.repayment.service;

import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;

import javax.transaction.Transactional;

public interface RepaymentService {

    @Transactional
    LoanEntity makeRepayment(RepaymentRequestDTO repaymentRequest) throws RepaymentAmountInvalidException;

}
