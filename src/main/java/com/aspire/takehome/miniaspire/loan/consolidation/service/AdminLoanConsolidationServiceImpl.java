package com.aspire.takehome.miniaspire.loan.consolidation.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminLoanConsolidationServiceImpl implements AdminLoanConsolidationService {

    private final LoanRepository loanRepository;

    private final UserDao userDao;

    @Override
    public List<LoanEntity> getLoansWithStatuses(List<LoanStatus> statuses) {
        return loanRepository.findByStatusIn(statuses);
    }

    @Override
    public List<LoanEntity> getLoansByUsername(String username) throws UserNotFoundException {
        UserEntity user = userDao.fetchByUsername(username);
        return loanRepository.findByUser(user);
    }
}

