package com.jsp.ai_service.repository;

import com.jsp.ai_service.model.OutputSchemaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface SchemaAdminRepository extends JpaRepository<OutputSchemaEntity, BigInteger> {
}