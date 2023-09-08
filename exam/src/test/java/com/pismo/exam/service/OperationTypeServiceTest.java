package com.pismo.exam.service;

import com.pismo.exam.domain.OperationType;
import com.pismo.exam.dto.OperationTypeDTO;
import com.pismo.exam.repository.OperationTypeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import static com.pismo.exam.helper.ConstantsHelper.PAGAMENTO;
import static com.pismo.exam.helper.ConstantsHelper.SAQUE;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationTypeServiceTest {

    @InjectMocks
    private OperationTypeService operationTypeService;

    @Mock
    private OperationTypeRepository operationTypeRepository;

    @Test
    public void shouldCreateOperation() {

        OperationTypeDTO operationTypeDTO = new OperationTypeDTO(PAGAMENTO);


        operationTypeService.createOperation(operationTypeDTO);


        verify(operationTypeRepository).save(argThat(entity ->
                entity.getDescription().equals((PAGAMENTO))));
    }

    @Test
    public void shouldFindOperationTypeById() {
        OperationType sampleOperationType = new OperationType();
        sampleOperationType.setId(1L);
        sampleOperationType.setDescription(SAQUE);

        when(operationTypeRepository.findById(1L)).thenReturn(Optional.of(sampleOperationType));

        Optional<OperationType> result = operationTypeService.findOperationTypeById(1L);

        verify(operationTypeRepository).findById(1L);

        assertTrue(result.isPresent());
        assertEquals(SAQUE, result.get().getDescription());
    }

    @Test
    public void shouldNotFindOperationTypeById() {
        when(operationTypeRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<OperationType> result = operationTypeService.findOperationTypeById(2L);

        verify(operationTypeRepository).findById(2L);

        assertFalse(result.isPresent());
    }
}