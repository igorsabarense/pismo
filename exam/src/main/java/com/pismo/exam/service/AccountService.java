package com.pismo.exam.service;

import com.pismo.exam.domain.Account;
import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(AccountDTO accountDTO){

        if(accountDTO.document_number().isBlank()){
            throw new BusinessException("Document_number cannot be blank.");
        }
        if(isDuplicatedDocumentNumber(accountDTO)){
            throw new BusinessException("There is already a resource with this document_number");
        }

        final Account account = new Account();
        account.setDocumentNumber(accountDTO.document_number());
        accountRepository.save(account);
    }

    public Optional<Account> findAccountById(Long id){
        return accountRepository.findById(id);
    }

    private boolean isDuplicatedDocumentNumber(AccountDTO accountDTO) {
        return Boolean.TRUE.equals(accountRepository.existsByDocumentNumber(accountDTO.document_number()));
    }




}
