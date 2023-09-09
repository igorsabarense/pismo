package com.pismo.exam.repository;

import com.pismo.exam.domain.Account;
import com.pismo.exam.domain.OperationType;
import com.pismo.exam.domain.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static com.pismo.exam.helper.ConstantsHelper.PAGAMENTO;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    private Account testAccount;

    private OperationType testOperationType;

    @BeforeEach
    public void setup() {
        testAccount = new Account();
        testAccount.setDocumentNumber("12345");

        testOperationType = new OperationType();
        testOperationType.setDescription(PAGAMENTO);

        accountRepository.save(testAccount);
        operationTypeRepository.save(testOperationType);
    }

    @Test
    void testSaveAndFindById() {
        Transaction transaction = new Transaction();
        transaction.setAccount(testAccount);
        transaction.setOperationType(testOperationType);
        transaction.setAmount(BigDecimal.TEN);



        Transaction savedTransaction = transactionRepository.save(transaction);

        Transaction retrievedTransaction = transactionRepository.findById(savedTransaction.getId()).orElse(null);

        assertNotNull(retrievedTransaction);
        assertEquals(BigDecimal.TEN, retrievedTransaction.getAmount());
        assertNotNull(retrievedTransaction.getEventDate());
    }

}
