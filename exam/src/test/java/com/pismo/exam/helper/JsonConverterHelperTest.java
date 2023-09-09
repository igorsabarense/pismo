package com.pismo.exam.helper;

import com.pismo.exam.dto.AccountDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class JsonConverterHelperTest {

    @Test
    void testAsJsonString() {
        AccountDTO accountDTO = new AccountDTO(1L, "1456789123");

        String jsonString = JsonConverterHelper.asJsonString(accountDTO);

        String expectedJsonString = "{\"account_id\":1,\"document_number\":\"1456789123\"}";

        assertEquals(expectedJsonString, jsonString);

    }

    @Test
    void testAsJsonStringException() {

        Object nonSerializableObject = new Object();

        assertThrows(RuntimeException.class, () -> JsonConverterHelper.asJsonString(nonSerializableObject));
    }
}
