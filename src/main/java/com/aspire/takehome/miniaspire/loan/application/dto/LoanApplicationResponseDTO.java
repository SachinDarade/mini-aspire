package com.aspire.takehome.miniaspire.loan.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoanApplicationResponseDTO {
    private Long loanId;
    private LocalDate applicationDate;
}

