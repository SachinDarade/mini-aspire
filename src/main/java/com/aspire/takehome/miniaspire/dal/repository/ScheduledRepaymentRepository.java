package com.aspire.takehome.miniaspire.dal.repository;

import com.aspire.takehome.miniaspire.dal.entity.ScheduledRepaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledRepaymentRepository extends JpaRepository<ScheduledRepaymentEntity, Long> {
    // You can add custom query methods here if needed
    // For example, finding repayments by loan ID or by status
}

