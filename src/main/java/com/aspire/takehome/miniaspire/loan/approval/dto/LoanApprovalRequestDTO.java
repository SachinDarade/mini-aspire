package com.aspire.takehome.miniaspire.loan.approval.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoanApprovalRequestDTO {
    private Long loanId;
    private boolean approved;
}

