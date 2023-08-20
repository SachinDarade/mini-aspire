package com.aspire.takehome.miniaspire.loan.consolidation.dto;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsolidationResponseDTO {
    private List<LoanEntity> loans;
}
