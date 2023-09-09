package com.pismo.exam.service;

import com.pismo.exam.domain.Account;
import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    private AccountDTO accountDTO;

    @BeforeEach
    void setUp(){
        accountDTO = new AccountDTO(null, "123456789");
    }

    @Test
    void shouldCreateAccount() {
        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        accountService.createAccount(accountDTO);
        verify(accountRepository).save(accountCaptor.capture());
        Account capturedAccount = accountCaptor.getValue();
        assertEquals(accountDTO.document_number(), capturedAccount.getDocumentNumber());
    }

    @Test
    void shouldFindAccountById() {
        Account sampleAccount = new Account();
        sampleAccount.setId(1L);
        sampleAccount.setDocumentNumber("123456789");

        when(accountRepository.findById(1L)).thenReturn(Optional.of(sampleAccount));

        Optional<Account> result = accountService.findAccountById(1L);

        verify(accountRepository).findById(1L);

        assertTrue(result.isPresent());
        assertEquals("123456789", result.get().getDocumentNumber());
    }

    @Test
    void shouldNotFindAccountById() {
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Account> result = accountService.findAccountById(2L);

        verify(accountRepository).findById(2L);

        assertFalse(result.isPresent());
    }
}