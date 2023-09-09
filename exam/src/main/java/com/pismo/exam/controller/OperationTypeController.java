package com.pismo.exam.controller;

import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.service.OperationTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operation-types")
public class OperationTypeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperationTypeController.class);
    private final OperationTypeService operationTypeService;

    @Autowired
    public OperationTypeController(OperationTypeService operationTypeService) {
        this.operationTypeService = operationTypeService;
    }


    @PostMapping
    public ResponseEntity<HttpStatus> createOperationType(@RequestBody OperationTypeDTO operationTypeDTO){
        LOGGER.info("Request to create operation type {}", operationTypeDTO);
        operationTypeService.createOperation(operationTypeDTO);
        LOGGER.info("Resource successfully created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{operation_type_id}")
    public ResponseEntity<OperationTypeDTO> findById(@PathVariable(name = "operation_type_id") Long operationTypeId){
        LOGGER.info("Request to find operation type with ID:{}", operationTypeId);
        OperationTypeDTO operationType = operationTypeService.findOperationTypeById(operationTypeId)
                .map(opType -> new OperationTypeDTO(opType.getDescription()))
                .orElseThrow(() -> new EntityNotFoundException("OperationType", operationTypeId));
        LOGGER.info("Resource found with ID:{} -> {}", operationTypeId, operationType);
        return ResponseEntity.ok(operationType);
    }
}
