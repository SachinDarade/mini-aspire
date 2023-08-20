package com.aspire.takehome.miniaspire.dal.entity;

import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
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
public class RepaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @NonNull
    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "amountPaid")
    private Double amountPaid;

    @NonNull
    @Column(name = "status")
    private RepaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "loan_id")
    private LoanEntity loan;

}

