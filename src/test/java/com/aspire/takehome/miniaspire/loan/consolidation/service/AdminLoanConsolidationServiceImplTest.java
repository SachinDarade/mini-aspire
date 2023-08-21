package com.aspire.takehome.miniaspire.loan.consolidation.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminLoanConsolidationServiceImplTest {

    private AdminLoanConsolidationServiceImpl adminLoanConsolidationService;
    private LoanRepository loanRepository;
    private UserDao userDao;

    @Before
    public void setup() {
        loanRepository = mock(LoanRepository.class);
        userDao = mock(UserDao.class);
        adminLoanConsolidationService = new AdminLoanConsolidationServiceImpl(loanRepository, userDao);
    }

    @Test
    public void testGetLoansWithStatuses_Success() {
        List<LoanStatus> statuses = Arrays.asList(LoanStatus.APPROVED, LoanStatus.PAID);

        LoanEntity loan1 = new LoanEntity();
        LoanEntity loan2 = new LoanEntity();

        when(loanRepository.findByStatusIn(statuses)).thenReturn(Arrays.asList(loan1, loan2));

        List<LoanEntity> loans = adminLoanConsolidationService.getLoansWithStatuses(statuses);

        assertEquals(2, loans.size());
        verify(loanRepository, times(1)).findByStatusIn(statuses);
    }

    @Test
    public void testGetLoansByUsername_Success() throws UserNotFoundException {
        String username = "testuser";
        UserEntity user = new UserEntity();

        when(userDao.fetchByUsername(username)).thenReturn(user);
        when(loanRepository.findByUser(user)).thenReturn(new ArrayList<>());

        List<LoanEntity> loans = adminLoanConsolidationService.getLoansByUsername(username);

        assertNotNull(loans);
        assertEquals(0, loans.size());
        verify(userDao, times(1)).fetchByUsername(username);
        verify(loanRepository, times(1)).findByUser(user);
    }

    @Test
    public void testGetLoansByUsername_UserNotFoundException() throws UserNotFoundException {
        String username = "nonexistentuser";

        when(userDao.fetchByUsername(username)).thenThrow(new UserNotFoundException("User not found"));

        assertThrows(UserNotFoundException.class, () -> adminLoanConsolidationService.getLoansByUsername(username));
        verify(userDao, times(1)).fetchByUsername(username);
        verify(loanRepository, never()).findByUser(any());
    }
}
