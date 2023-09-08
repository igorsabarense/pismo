package com.pismo.exam.exception.advice;


import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Status> handleEntityNotFoundException(EntityNotFoundException ex) {
        return new ResponseEntity<>(new Status("Vital resource not found.",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Status> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(new Status("Invalid operation has been performed.", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
