package com.aspire.takehome.miniaspire.loan.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovalRequestDTO {
    @NotNull
    private Long loanId;
    private boolean approved;
}

