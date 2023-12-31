package com.pismo.exam.repository;

import com.pismo.exam.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void testSaveAndFindById() {

        Account account = new Account();
        account.setDocumentNumber("12345");


        Account savedAccount = accountRepository.save(account);


        Account retrievedAccount = accountRepository.findById(savedAccount.getId()).orElse(null);


        assertNotNull(retrievedAccount);
        assertEquals("12345", retrievedAccount.getDocumentNumber());
    }

}
