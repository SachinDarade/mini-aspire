package com.aspire.takehome.miniaspire.loan.approval.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.exceptions.InvalidStateException;
import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LoanApprovalServiceImplTest {

    private LoanApprovalServiceImpl loanApprovalService;
    private LoanRepository loanRepository;

    @Before
    public void setup() {
        loanRepository = mock(LoanRepository.class);
        loanApprovalService = new LoanApprovalServiceImpl(loanRepository);
    }

    @Test
    public void testApproveLoan_Success() {
        LoanEntity pendingLoan = new LoanEntity();
        pendingLoan.setStatus(LoanStatus.PENDING);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(pendingLoan));
        when(loanRepository.save(any())).thenReturn(pendingLoan);

        LoanEntity approvedLoan = loanApprovalService.approveLoan(1L, true);

        assertEquals(LoanStatus.APPROVED, approvedLoan.getStatus());
        verify(loanRepository, times(1)).findById(anyLong());
        verify(loanRepository, times(1)).save(any());
    }

    @Test
    public void testRejectLoan_Success() {
        LoanEntity pendingLoan = new LoanEntity();
        pendingLoan.setStatus(LoanStatus.PENDING);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(pendingLoan));
        when(loanRepository.save(any())).thenReturn(pendingLoan);

        LoanEntity rejectedLoan = loanApprovalService.approveLoan(1L, false);

        assertEquals(LoanStatus.REJECTED, rejectedLoan.getStatus());
        verify(loanRepository, times(1)).findById(anyLong());
        verify(loanRepository, times(1)).save(any());
    }

    @Test
    public void testApproveLoan_InvalidStateException() {
        LoanEntity approvedLoan = new LoanEntity();
        approvedLoan.setStatus(LoanStatus.APPROVED);

        when(loanRepository.findById(anyLong())).thenReturn(Optional.of(approvedLoan));

        assertThrows(InvalidStateException.class, () -> loanApprovalService.approveLoan(1L, true));
        verify(loanRepository, times(1)).findById(anyLong());
        verify(loanRepository, never()).save(any());
    }

    @Test
    public void testApproveLoan_LoanNotFoundException() {
        when(loanRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(LoanNotFoundException.class, () -> loanApprovalService.approveLoan(1L, true));
        verify(loanRepository, times(1)).findById(anyLong());
        verify(loanRepository, never()).save(any());
    }
}
