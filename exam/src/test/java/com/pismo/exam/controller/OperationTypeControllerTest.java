package com.pismo.exam.controller;

import com.pismo.exam.domain.OperationType;
import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.helper.JsonConverterHelper;
import com.pismo.exam.service.OperationTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static com.pismo.exam.helper.ConstantsHelper.PAGAMENTO;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OperationTypeController.class)
class OperationTypeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OperationTypeService operationTypeService;



    @Test
    void shouldCreateOperationType() throws Exception {

        OperationTypeDTO operationTypeDTO = new OperationTypeDTO(PAGAMENTO);

        doNothing().when(operationTypeService).createOperation(operationTypeDTO);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/operation-types")
                        .content(JsonConverterHelper.asJsonString(operationTypeDTO))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }


    @Test
    void shouldGetOperationTypeById() throws Exception {

        when(operationTypeService.findOperationTypeById(1L)).thenReturn(Optional.of(new OperationType(1L, PAGAMENTO)));

        mockMvc.perform(MockMvcRequestBuilders.get("/operation-types/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value(PAGAMENTO));
    }
}