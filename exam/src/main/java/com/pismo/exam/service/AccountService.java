package com.pismo.exam.service;

import com.pismo.exam.domain.Account;
import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(AccountDTO accountDTO){
        final Account account = new Account();
        account.setDocumentNumber(accountDTO.document_number());
        accountRepository.save(account);
    }

    public Optional<Account> findAccountById(Long id){
        return accountRepository.findById(id);
    }



}
