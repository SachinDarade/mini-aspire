package com.aspire.takehome.miniaspire.loan.consolidation.dto;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConsolidationRequestDTO {
    List<LoanStatus> statuses;
}
