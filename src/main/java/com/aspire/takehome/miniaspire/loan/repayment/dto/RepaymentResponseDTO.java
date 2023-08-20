package com.aspire.takehome.miniaspire.loan.repayment.dto;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentResponseDTO {
    private LoanEntity loan;
    private String message;     // Will hold the message if the loan is fully paid, remaining amount, etc
}

