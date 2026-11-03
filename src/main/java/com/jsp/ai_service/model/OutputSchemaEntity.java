package com.jsp.ai_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "ai_output_schema")
public class OutputSchemaEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alt_key")
    private BigInteger altKey;

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantId;

    @Column(name = "schema_code", nullable = false)
    private String schemaCode;

    @Column(unique = true)
    private int version;

    @Column(name = "status_flag")
    private String statusFlag;

    @Lob
    @Column(name = "json_schema")
    private String jsonSchema;

}