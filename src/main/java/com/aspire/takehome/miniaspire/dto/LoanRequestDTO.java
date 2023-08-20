package com.aspire.takehome.miniaspire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoanRequestDTO {
    private Double amountRequired;
    private Integer loanTerm;
}

