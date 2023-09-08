package com.pismo.exam.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EntityNotFoundExceptionTest {

    @Test
    public void testDefaultConstructor() {
        EntityNotFoundException exception = new EntityNotFoundException();
        assertNull(exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithMessage() {
        EntityNotFoundException exception = new EntityNotFoundException("Custom error message");
        assertEquals("Custom error message", exception.getMessage());
        assertNull(exception.getCause());
    }

    @Test
    public void testConstructorWithCause() {
        Throwable cause = new RuntimeException("Root cause");
        EntityNotFoundException exception = new EntityNotFoundException(cause);
        assertEquals("java.lang.RuntimeException: Root cause", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithMessageAndCause() {
        Throwable cause = new RuntimeException("Root cause");
        EntityNotFoundException exception = new EntityNotFoundException("Custom error message", cause);
        assertEquals("Custom error message", exception.getMessage());
        assertEquals(cause, exception.getCause());
    }

    @Test
    public void testConstructorWithEntityTypeAndId() {
        EntityNotFoundException exception = new EntityNotFoundException("Entity", 42L);
        assertEquals("Entity not found for ID: 42", exception.getMessage());
        assertNull(exception.getCause());
    }
}
