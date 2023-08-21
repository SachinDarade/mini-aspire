package com.aspire.takehome.miniaspire.loan.approval.dto;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanApprovalResponseDTO {
    private Long loadId;
    private LoanStatus newLoanStatus;
}

