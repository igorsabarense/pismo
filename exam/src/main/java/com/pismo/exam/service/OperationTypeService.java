package com.pismo.exam.service;

import com.pismo.exam.domain.OperationType;
import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.repository.OperationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperationTypeService {

    private final OperationTypeRepository operationTypeRepository;

    @Autowired
    public OperationTypeService(OperationTypeRepository operationTypeRepository) {
        this.operationTypeRepository = operationTypeRepository;
    }

    public void createOperation(OperationTypeDTO operationTypeDTO){
        final OperationType entity = new OperationType();
        entity.setDescription(operationTypeDTO.description());
        operationTypeRepository.save(entity);
    }

    public Optional<OperationType> findOperationTypeById(Long id){
        return operationTypeRepository.findById(id);
    }

}
