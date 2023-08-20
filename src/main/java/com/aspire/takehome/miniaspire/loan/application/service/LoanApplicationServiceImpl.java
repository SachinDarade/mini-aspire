package com.aspire.takehome.miniaspire.loan.application.service;

import com.aspire.takehome.miniaspire.common.enums.LoanStatus;
import com.aspire.takehome.miniaspire.common.enums.RepaymentStatus;
import com.aspire.takehome.miniaspire.common.exceptions.UserNotFoundException;
import com.aspire.takehome.miniaspire.dal.dao.UserDao;
import com.aspire.takehome.miniaspire.dal.entity.LoanEntity;
import com.aspire.takehome.miniaspire.dal.entity.RepaymentEntity;
import com.aspire.takehome.miniaspire.dal.entity.UserEntity;
import com.aspire.takehome.miniaspire.dal.repository.LoanRepository;
import com.aspire.takehome.miniaspire.dal.repository.RepaymentRepository;
import com.aspire.takehome.miniaspire.loan.application.dto.LoanApplicationRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanRepository loanRepository;

    private final RepaymentRepository repaymentRepository;

    private final UserDao userDao;

    @Override
    @Transactional
    public LoanEntity createLoan(LoanApplicationRequestDTO loanRequest,
                                 String userName) throws UserNotFoundException {
        UserEntity user = userDao.fetchByUsername(userName);
        LoanEntity loan = new LoanEntity(
                null,
                loanRequest.getAmountRequired(),
                0.0,
                loanRequest.getLoanTerm(),
                LocalDate.now(),
                LoanStatus.PENDING,
                user
        );
        LoanEntity savedLoan = loanRepository.save(loan);

        // Calculate repayments and save them
        calculateAndSaveRepayments(savedLoan);

        return savedLoan;
    }

    private void calculateAndSaveRepayments(LoanEntity loan) {
        int loanTerm = loan.getLoanTerm();
        LocalDate currentDate = LocalDate.now();

        for (int i = 1; i <= loanTerm; i++) {
            RepaymentEntity repayment = new RepaymentEntity();
            repayment.setDueDate(currentDate.plusWeeks(i));
            repayment.setAmountPaid(0.0); // Initialize amount paid to 0
            repayment.setStatus(RepaymentStatus.PENDING);
            repayment.setLoan(loan);

            repaymentRepository.save(repayment);
        }
    }
}
