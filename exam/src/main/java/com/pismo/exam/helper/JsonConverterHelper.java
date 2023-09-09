package com.pismo.exam.helper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverterHelper {

    private JsonConverterHelper(){}
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
