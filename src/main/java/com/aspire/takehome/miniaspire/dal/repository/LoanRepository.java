package com.aspire.takehome.miniaspire.dal.repository;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    List<LoanEntity> findByStatusIn(List<LoanStatus> statuses);

    List<LoanEntity> findByUser(UserEntity user);

    List<LoanEntity> findByUserId(Long userId);
}

