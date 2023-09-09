package com.pismo.exam.repository;

import com.pismo.exam.domain.OperationType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class OperationTypeRepositoryTest {

    @Autowired
    private OperationTypeRepository operationTypeRepository;

    @Test
    void testFindById() {

        OperationType operationType = new OperationType();
        operationType.setDescription("DUMMY");
        operationTypeRepository.save(operationType);


        OperationType retrievedOperationType = operationTypeRepository.findById(operationType.getId()).orElse(null);

        assertNotNull(retrievedOperationType);
        assertEquals("DUMMY", retrievedOperationType.getDescription());
    }
}
