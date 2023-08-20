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
    private Long id;
    private Double amountRequired;
    private Integer loanTerm;
    private LocalDate applicationDate;
    private Double amountPaid;
    private LoanStatus status;

}

