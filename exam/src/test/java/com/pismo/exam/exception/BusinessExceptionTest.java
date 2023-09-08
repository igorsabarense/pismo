package com.pismo.exam.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessExceptionTest {

    @Test
    public void testDefaultConstructor() {
        BusinessException exception = new BusinessException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        BusinessException exception = new BusinessException("Custom error message");
        assertEquals("Custom error message", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Root cause");
        BusinessException exception = new BusinessException(cause);
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");
        BusinessException exception = new BusinessException("Custom error message", cause);
        assertEquals("Custom error message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}