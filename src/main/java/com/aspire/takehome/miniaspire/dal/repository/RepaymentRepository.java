package com.aspire.takehome.miniaspire.dal.repository;

import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepaymentRepository extends JpaRepository<RepaymentEntity, Long> {

    List<RepaymentEntity> findByLoanIdIn(List<Long> loanIds);

}

