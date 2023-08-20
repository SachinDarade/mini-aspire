package com.aspire.takehome.miniaspire.dal.entity;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@Entity
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amountRequired;
    private Double amountPaid;
    private Integer loanTerm;
    private LocalDate applicationDate;
    private LoanStatus status;
}

