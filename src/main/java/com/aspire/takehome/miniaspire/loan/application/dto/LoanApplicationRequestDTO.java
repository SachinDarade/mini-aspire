package com.aspire.takehome.miniaspire.loan.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoanApplicationRequestDTO {
    @Positive(message = "Amount should be positive")
    private Double amountRequired;
    @Min(value = 1, message = "Minimum term must be 1")
    private Integer loanTerm;
}

