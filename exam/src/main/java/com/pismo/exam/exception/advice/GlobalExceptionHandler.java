package com.pismo.exam.exception.advice;


import com.pismo.exam.exception.BusinessException;
import com.pismo.exam.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Status> handleEntityNotFoundException(EntityNotFoundException ex) {
        LOGGER.error("Request failed.");
        return new ResponseEntity<>(new Status("Vital resource not found.",ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Status> handleBusinessException(BusinessException ex) {
        LOGGER.error("Request failed.");
        return new ResponseEntity<>(new Status("Invalid operation has been performed.", ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
