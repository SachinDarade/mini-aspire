package com.aspire.takehome.miniaspire.dal.repository;

import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepaymentRepository extends JpaRepository<RepaymentEntity, Long> {

    List<RepaymentEntity> findByLoanIdIn(List<Long> loanIds);

    Optional<RepaymentEntity> findFirstByLoanIdAndStatusOrderByDueDateAsc(Long loanId,
                                                                          RepaymentStatus repaymentStatus);

    boolean existsByLoanAndStatus(LoanEntity loan, RepaymentStatus repaymentStatus);
}

