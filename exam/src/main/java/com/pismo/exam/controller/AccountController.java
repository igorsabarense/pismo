package com.pismo.exam.controller;

import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(final AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createAccount(@RequestBody AccountDTO account){
        accountService.createAccount(account);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountDTO> getAccountById(@PathVariable Long accountId){
        AccountDTO accountDTO = accountService.findAccountById(accountId).map(
                account -> new AccountDTO(account.getId(), account.getDocumentNumber())
        ).orElseThrow(() -> new EntityNotFoundException("Account", accountId));

        return ResponseEntity.ok(accountDTO);

    }
}
