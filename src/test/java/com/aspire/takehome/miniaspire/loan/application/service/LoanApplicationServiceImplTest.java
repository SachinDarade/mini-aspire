package com.aspire.takehome.miniaspire.loan.application.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoanApplicationServiceImplTest {

    @InjectMocks
    private LoanApplicationServiceImpl loanApplicationService;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private RepaymentRepository repaymentRepository;

    @Mock
    private UserDao userDao;

    @Test
    public void givenValidLoanRequest_whenCreateLoan_then1LoanTableEntryAnd3RepaymentTableEntries() throws UserNotFoundException {
        // Create a mock UserEntity and LoanApplicationRequestDTO
        UserEntity user = new UserEntity();
        when(userDao.fetchByUsername(anyString())).thenReturn(user);

        LoanApplicationRequestDTO loanRequest = new LoanApplicationRequestDTO(
                10000.0,
                3
        );

        // Mock the loanRepository and repaymentRepository behaviors
        when(loanRepository.save(any())).thenReturn(new LoanEntity(
                1L,
                10000.0,
                0.0,
                3,
                LocalDate.now(),
                LoanStatus.PENDING,
                new UserEntity()
        ));
        when(repaymentRepository.save(any())).thenReturn(new RepaymentEntity());

        // Call the method
        LoanEntity createdLoan = loanApplicationService.createLoan(loanRequest, "username");

        // Assert
        assertNotNull(createdLoan);
        assertEquals(LoanStatus.PENDING, createdLoan.getStatus());
        verify(loanRepository, times(1)).save(any());
        verify(repaymentRepository, times(3)).save(any());
    }

}
