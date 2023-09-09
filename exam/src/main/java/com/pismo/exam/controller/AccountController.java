package com.pismo.exam.controller;

import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountDTO account){
        LOGGER.info("Request to create an account {}", account);
        accountService.createAccount(account);
        LOGGER.info("Resource successfully created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId){
        LOGGER.info("Request to find an account with ID:{}", accountId);
        AccountDTO accountDTO = accountService.findAccountById(accountId).map(
                account -> new AccountDTO(account.getId(), account.getDocumentNumber())
        ).orElseThrow(() -> new EntityNotFoundException("Account", accountId));
        LOGGER.info("Resource found with ID:{} -> {}", accountId, accountDTO);
        return ResponseEntity.ok(accountDTO);

    }
}
