package com.pismo.exam.controller;

import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.dto.TransactionDTO;
import com.pismo.exam.helper.JsonConverterHelper;
import com.pismo.exam.service.AccountService;
import com.pismo.exam.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransactionController.class)
class TransactionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Test
    void shouldCreateTransaction() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO(1L,1L, BigDecimal.TEN);

        doNothing().when(transactionService).createTransaction(transactionDTO);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/transactions")
                        .content(JsonConverterHelper.asJsonString(transactionDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}