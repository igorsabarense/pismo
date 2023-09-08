package com.pismo.exam.service;

import com.pismo.exam.domain.Account;
import com.pismo.exam.domain.OperationType;
import com.pismo.exam.domain.Transaction;
import com.pismo.exam.dto.TransactionDTO;
import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.pismo.exam.helper.ConstantsHelper.*;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private final AccountService accountService;

    private final OperationTypeService operationTypeService;



    @Autowired
    public TransactionService(final TransactionRepository transactionRepository,
                              final AccountService accountService,
                              final OperationTypeService operationTypeService) {
        this.transactionRepository = transactionRepository;
        this.accountService = accountService;
        this.operationTypeService = operationTypeService;
    }

    public void createTransaction(final TransactionDTO transactionDTO){
        Account account = getAccount(transactionDTO);
        OperationType operationType = getOperationType(transactionDTO);

        final Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setOperationType(operationType);
        transaction.setAmount(transactionDTO.amount());

        validateTransaction(transaction);

        transactionRepository.save(transaction);
    }

    private OperationType getOperationType(TransactionDTO transactionDTO) {
        return operationTypeService.findOperationTypeById(transactionDTO.operation_type_id())
                .orElseThrow(() -> new EntityNotFoundException("OperationType", transactionDTO.operation_type_id()));
    }

    private Account getAccount(TransactionDTO transactionDTO) {
        return accountService.findAccountById(transactionDTO.account_id())
                .orElseThrow(() -> new EntityNotFoundException("Account", transactionDTO.account_id()));
    }

    private void validateTransaction(Transaction transaction) {
        OperationType operationType = transaction.getOperationType();
        BigDecimal amount = transaction.getAmount();

        switch (operationType.getDescription()) {
            case COMPRA_A_VISTA, COMPRA_PARCELADA , SAQUE -> {
                if (amount.signum() >= 0) {
                    throw new BusinessException("Amount must be negative for operation: " + transaction.getOperationType().getDescription());
                }
            }
            case PAGAMENTO -> {
                if (amount.signum() <= 0) {
                    throw new BusinessException("Amount must be positive for operation: " + transaction.getOperationType().getDescription());
                }
            }
        }
    }

}

