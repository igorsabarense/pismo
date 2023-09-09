package com.pismo.exam.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pismo.exam.domain.Account;
import com.pismo.exam.dto.AccountDTO;
import com.pismo.exam.helper.JsonConverterHelper;
import com.pismo.exam.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AccountController.class)
class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;



    @Test
    void shouldGetAccountById() throws Exception {

        AccountDTO accountDTO = new AccountDTO(null,"1234567890");

        doNothing().when(accountService).createAccount(accountDTO);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/accounts")
                        .content(JsonConverterHelper.asJsonString(accountDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldCreateAccount() throws Exception {

        when(accountService.findAccountById(1L)).thenReturn(Optional.of(new Account(1L, "123456789")));

        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.account_id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.document_number").value("123456789"));
    }

}
