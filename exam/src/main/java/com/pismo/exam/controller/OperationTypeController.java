package com.pismo.exam.controller;

import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.exception.EntityNotFoundException;
import com.pismo.exam.service.OperationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{operation_type_id}")
    public ResponseEntity<OperationTypeDTO> findById(@PathVariable(name = "operation_type_id") Long operationTypeId){
        OperationTypeDTO operationType = operationTypeService.findOperationTypeById(operationTypeId)
                .map(opType -> new OperationTypeDTO(opType.getDescription()))
                .orElseThrow(() -> new EntityNotFoundException("OperationType", operationTypeId));
        return ResponseEntity.ok(operationType);
    }
}
