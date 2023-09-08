package com.pismo.exam.controller;

import com.pismo.exam.dto.TransactionDTO;
import com.pismo.exam.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createTransaction(@RequestBody TransactionDTO transactionDTO){
        transactionService.createTransaction(transactionDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
