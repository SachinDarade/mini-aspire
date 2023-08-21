package com.aspire.takehome.miniaspire.loan.repayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentRequestDTO {
    @NotNull
    private Long loanId;
    @Min(value = 0)
    private Double amount;
}

