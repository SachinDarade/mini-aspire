package com.aspire.takehome.miniaspire.loan.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoanApplicationRequestDTO {
    private Double amountRequired;
    private Integer loanTerm;
}

