package com.aspire.takehome.miniaspire.loan.repayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentRequestDTO {
    private Long loanId;
    private Double amount;
}

