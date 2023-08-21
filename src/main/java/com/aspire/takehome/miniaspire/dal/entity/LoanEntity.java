package com.aspire.takehome.miniaspire.dal.entity;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LoanEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "amountRequired", nullable = false)
    private Double amountRequired;

    @Column(name = "amountPaid")
    private Double amountPaid;

    @Column(name = "loanTerm", nullable = false)
    private Integer loanTerm;

    @Column(name = "applicationDate", nullable = false)
    private LocalDate applicationDate;

    @Column(name = "status")
    private LoanStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;    // Relating Loan with User

}

