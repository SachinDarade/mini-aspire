package com.aspire.takehome.miniaspire.loan.repayment.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import com.aspire.takehome.miniaspire.common.exceptions.LoanNotFoundException;
import com.aspire.takehome.miniaspire.common.exceptions.RepaymentAmountInvalidException;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import com.aspire.takehome.miniaspire.loan.repayment.dto.RepaymentRequestDTO;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class RepaymentServiceImplTest {

    private RepaymentServiceImpl repaymentService;
    private RepaymentRepository repaymentRepository;
    private LoanRepository loanRepository;

    @Before
    public void setup() {
        repaymentRepository = mock(RepaymentRepository.class);
        loanRepository = mock(LoanRepository.class);
        repaymentService = new RepaymentServiceImpl(repaymentRepository, loanRepository);
    }

    @Test
    public void testMakeRepayment_Success() throws RepaymentAmountInvalidException {
        Long loanId = 1L;
        Double repaymentAmount = 100.0;

        LoanEntity loan = new LoanEntity();
        loan.setId(loanId);
        loan.setAmountRequired(500.0);
        loan.setAmountPaid(0.0);
        loan.setLoanTerm(5);
        loan.setStatus(LoanStatus.PENDING);

        RepaymentEntity repaymentEntity = new RepaymentEntity(
                1L,
                LocalDate.now(),
                0.0,
                RepaymentStatus.PENDING,
                loan
        );

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(repaymentRepository
                .findFirstByLoanIdAndStatusOrderByDueDateAsc(loanId, RepaymentStatus.PENDING))
                .thenReturn(Optional.of(repaymentEntity));
        when(repaymentRepository.save(any())).thenReturn(repaymentEntity);
        when(loanRepository.save(any())).thenReturn(loan);

        RepaymentRequestDTO repaymentRequest = new RepaymentRequestDTO(loanId, repaymentAmount);
        LoanEntity updatedLoan = repaymentService.makeRepayment(repaymentRequest);

        assertEquals(RepaymentStatus.PAID, repaymentEntity.getStatus());
        assertEquals(100.0, repaymentEntity.getAmountPaid(), 0.2);
        assertEquals(100.0, loan.getAmountPaid(), 0.2);
        assertEquals(LoanStatus.PENDING, updatedLoan.getStatus());

        verify(loanRepository, times(1)).findById(loanId);
        verify(repaymentRepository, times(1)).findFirstByLoanIdAndStatusOrderByDueDateAsc(loanId, RepaymentStatus.PENDING);
        verify(repaymentRepository, times(1)).save(any());
        verify(loanRepository, times(1)).save(any());
    }

    @Test
    public void testMakeRepayment_InvalidRepaymentAmount() {
        Long loanId = 1L;
        Double repaymentAmount = -50.0;

        LoanEntity loan = new LoanEntity();
        loan.setId(loanId);
        loan.setAmountRequired(500.0);
        loan.setAmountPaid(0.0);
        loan.setLoanTerm(5);
        loan.setStatus(LoanStatus.PENDING);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        RepaymentRequestDTO repaymentRequest = new RepaymentRequestDTO(loanId, repaymentAmount);

        assertThrows(RepaymentAmountInvalidException.class, () -> repaymentService.makeRepayment(repaymentRequest));
        verify(loanRepository, times(1)).findById(loanId);
        verify(repaymentRepository, never()).findFirstByLoanIdAndStatusOrderByDueDateAsc(any(), any());
        verify(repaymentRepository, never()).save(any());
        verify(loanRepository, never()).save(any());
    }

    @Test
    public void testMakeRepayment_LoanNotFound() {
        Long loanId = 1L;
        Double repaymentAmount = 100.0;

        when(loanRepository.findById(loanId)).thenReturn(Optional.empty());

        RepaymentRequestDTO repaymentRequest = new RepaymentRequestDTO(loanId, repaymentAmount);

        assertThrows(LoanNotFoundException.class, () -> repaymentService.makeRepayment(repaymentRequest));
        verify(loanRepository, times(1)).findById(loanId);
        verify(repaymentRepository, never()).findFirstByLoanIdAndStatusOrderByDueDateAsc(any(), any());
        verify(repaymentRepository, never()).save(any());
        verify(loanRepository, never()).save(any());
    }

    @Test
    public void testMakeRepayment_AllRepaymentsPaid() throws RepaymentAmountInvalidException {
        Long loanId = 1L;
        Double repaymentAmount = 100.0;

        LoanEntity loan = new LoanEntity();
        loan.setId(loanId);
        loan.setAmountRequired(100.0);
        loan.setAmountPaid(0.0);
        loan.setLoanTerm(5);
        loan.setStatus(LoanStatus.PENDING);

        RepaymentEntity repaymentEntity = new RepaymentEntity(
                1L,
                LocalDate.now(),
                0.0,
                RepaymentStatus.PENDING,
                loan
        );

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(repaymentRepository.findFirstByLoanIdAndStatusOrderByDueDateAsc(loanId, RepaymentStatus.PENDING))
                .thenReturn(Optional.of(repaymentEntity));
        when(loanRepository.save(loan)).thenReturn(loan);

        RepaymentRequestDTO repaymentRequest = new RepaymentRequestDTO(loanId, repaymentAmount);
        LoanEntity updatedLoan = repaymentService.makeRepayment(repaymentRequest);

        assertEquals(LoanStatus.PAID, updatedLoan.getStatus());

        verify(loanRepository, times(1)).findById(loanId);
        verify(repaymentRepository, times(1)).findFirstByLoanIdAndStatusOrderByDueDateAsc(loanId, RepaymentStatus.PENDING);
        verify(repaymentRepository, times(1)).save(any());
        verify(loanRepository, times(2)).save(any());
    }
}
