package com.aspire.takehome.miniaspire.loan.consolidation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class UserLoanConsolidationServiceImplTest {

    private UserLoanConsolidationServiceImpl userLoanConsolidationService;
    private LoanRepository loanRepository;
    private RepaymentRepository repaymentRepository;

    @Before
    public void setup() {
        loanRepository = mock(LoanRepository.class);
        repaymentRepository = mock(RepaymentRepository.class);
        userLoanConsolidationService = new UserLoanConsolidationServiceImpl(loanRepository, repaymentRepository);
    }

    @Test
    public void testGetUserLoans_Success() {
        Long userId = 1L;
        List<LoanEntity> loans = Arrays.asList(new LoanEntity(), new LoanEntity());

        when(loanRepository.findByUserId(userId)).thenReturn(loans);

        List<LoanEntity> userLoans = userLoanConsolidationService.getUserLoans(userId);

        assertEquals(2, userLoans.size());
        verify(loanRepository, times(1)).findByUserId(userId);
    }

    @Test
    public void testGetUserRepayments_Success() {
        Long userId = 1L;
        List<Long> loanIds = Arrays.asList(1L, 2L);

        List<LoanEntity> userLoans = Arrays.asList(
                new LoanEntity(
                        1L,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new UserEntity()
                ),
                new LoanEntity(
                        2L,
                        null,
                        null,
                        null,
                        null,
                        null,
                        new UserEntity()
                ));
        when(loanRepository.findByUserId(userId)).thenReturn(userLoans);

        List<RepaymentEntity> repayments = Arrays.asList(new RepaymentEntity(), new RepaymentEntity());
        when(repaymentRepository.findByLoanIdIn(loanIds)).thenReturn(repayments);

        List<RepaymentEntity> userRepayments = userLoanConsolidationService.getUserRepayments(userId);

        assertEquals(2, userRepayments.size());
        verify(loanRepository, times(1)).findByUserId(userId);
        verify(repaymentRepository, times(1)).findByLoanIdIn(loanIds);
    }

    @Test
    public void testGetUserLoansWithStatuses_Success() {
        Long userId = 1L;
        List<LoanStatus> statuses = Arrays.asList(LoanStatus.APPROVED, LoanStatus.PAID);

        List<LoanEntity> loans = Arrays.asList(new LoanEntity(), new LoanEntity());
        when(loanRepository.findByUserIdAndStatusIn(userId, statuses)).thenReturn(loans);

        List<LoanEntity> userLoans = userLoanConsolidationService.getUserLoans(userId, statuses);

        assertEquals(2, userLoans.size());
        verify(loanRepository, times(1)).findByUserIdAndStatusIn(userId, statuses);
    }
}
