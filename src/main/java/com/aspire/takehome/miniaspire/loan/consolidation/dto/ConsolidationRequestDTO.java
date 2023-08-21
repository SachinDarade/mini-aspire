package com.aspire.takehome.miniaspire.loan.consolidation.dto;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ConsolidationRequestDTO {
    @NonNull
    private List<LoanStatus> statuses;
}
