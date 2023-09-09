package com.pismo.exam.service;

import com.pismo.exam.domain.Account;
import com.pismo.exam.domain.OperationType;
import com.pismo.exam.dto.TransactionDTO;
import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.pismo.exam.helper.ConstantsHelper.COMPRA_A_VISTA;
import static com.pismo.exam.helper.ConstantsHelper.PAGAMENTO;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private AccountService accountService;

    @Mock
    private OperationTypeService operationTypeService;


    @Test
    void shouldCreateTransactionValidTransactionWhenNegativeAmount() {

        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, (BigDecimal.valueOf(-100.0)));

        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));
        when(operationTypeService.findOperationTypeById(2L)).thenReturn(Optional.of(new OperationType(2L, COMPRA_A_VISTA)));

        transactionService.createTransaction(transactionDTO);

        verify(transactionRepository).save(argThat(transaction ->
                transaction.getAccount() != null && transaction.getOperationType() != null
                        && transaction.getAmount().compareTo(BigDecimal.valueOf(-100.0)) == 0));
    }

    @Test
    void shouldCreateTransactionValidTransactionWhenPositiveAmount() {

        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, (BigDecimal.valueOf(100.0)));

        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));
        when(operationTypeService.findOperationTypeById(2L)).thenReturn(Optional.of(new OperationType(2L, PAGAMENTO)));

        transactionService.createTransaction(transactionDTO);

        verify(transactionRepository).save(argThat(transaction ->
                transaction.getAccount() != null && transaction.getOperationType() != null
                        && transaction.getAmount().compareTo(BigDecimal.valueOf(100.0)) == 0));
    }


    @Test
    void shouldThrowExceptionWhenNegativeAmountForOperationTypePagamento() {

        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, BigDecimal.valueOf(-100.0));

        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));
        when(operationTypeService.findOperationTypeById(2L)).thenReturn(Optional.of(new OperationType(2L, PAGAMENTO)));

        assertThrows(BusinessException.class, () -> transactionService.createTransaction(transactionDTO));
    }

    @ParameterizedTest
    @ValueSource(strings = {"COMPRA A VISTA", "COMPRA PARCELADA", "SAQUE"})
    void shouldThrowExceptionWhenPositiveAmountForOperationTypeCompraOrSaque(java.lang.String operationType) {
        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, BigDecimal.valueOf(100.0));
        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));
        when(operationTypeService.findOperationTypeById(2L)).thenReturn(Optional.of(new OperationType(2L, operationType)));
        assertThrows(BusinessException.class, () -> transactionService.createTransaction(transactionDTO));
    }

    @Test
    void shouldThrowExceptionWhenAccountIsNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, BigDecimal.valueOf(100.0));
        when(accountService.findAccountById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> transactionService.createTransaction(transactionDTO));
    }

    @Test
    void shouldThrowExceptionWhenOperationTypeIsNotFound() {
        TransactionDTO transactionDTO = new TransactionDTO(1L, 2L, BigDecimal.valueOf(100.0));
        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));
        when(operationTypeService.findOperationTypeById(2L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> transactionService.createTransaction(transactionDTO));
    }




}
