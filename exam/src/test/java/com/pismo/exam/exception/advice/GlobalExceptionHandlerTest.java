package com.pismo.exam.exception.advice;

import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler exceptionHandler;

    @Test
    void handleEntityNotFoundException() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found.");
        ResponseEntity<Status> response = exceptionHandler.handleEntityNotFoundException(ex);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Vital resource not found.", response.getBody().cause());
        assertEquals("Entity not found.", response.getBody().description());
    }

    @Test
    void handleBusinessException() {
        BusinessException ex = new BusinessException("Invalid operation.");
        ResponseEntity<Status> response = exceptionHandler.handleBusinessException(ex);

        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.getStatusCode());
        assertEquals("Invalid operation has been performed.", response.getBody().cause());
        assertEquals("Invalid operation.", response.getBody().description());
    }
}