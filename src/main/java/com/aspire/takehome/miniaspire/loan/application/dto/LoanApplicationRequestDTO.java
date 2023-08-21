package com.aspire.takehome.miniaspire.loan.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoanApplicationRequestDTO {
    @Min(value = 0)
    private Double amountRequired;
    @Min(value = 1)
    private Integer loanTerm;
}

