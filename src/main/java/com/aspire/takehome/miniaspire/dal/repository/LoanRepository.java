package com.aspire.takehome.miniaspire.dal.repository;

import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    // You can add custom query methods here if needed
    // For example, finding loans by status or by user ID
}

