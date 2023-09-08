package com.pismo.exam.dto;

import java.math.BigDecimal;

public record TransactionDTO(Long account_id, Long operation_type_id, BigDecimal amount){}

