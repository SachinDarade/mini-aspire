package com.aspire.takehome.miniaspire.dal.entity;

import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ScheduledRepaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dueDate;
    private Double amountPaid;
    private RepaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

}

