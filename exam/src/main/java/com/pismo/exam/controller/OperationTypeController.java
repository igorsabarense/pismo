package com.pismo.exam.controller;

import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation-types")
public class OperationTypeController {

    private final OperationTypeService operationTypeService;

    @Autowired
    public OperationTypeController(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createOperationType(@RequestBody OperationTypeDTO operationTypeDTO){
        operationTypeService.createOperation(operationTypeDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
