package com.jsp.ai_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Data
@Entity
@Table(name = "ai_agent_def")
public class AgentDefinitionEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alt_key", columnDefinition = "bigint")
    private BigInteger altKey;

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantId;

    @Column(name = "agent_code")
    private String agentCode;

    @Column(name = "agent_name", nullable = false)
    private String agentName;

    @Column(unique = true)
    private int version;

    @Column(name = "status_flag")
    private String statusFlag;

    @Column(name = "model_provider")
    private String modelProvider;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "allowed_models", length = 500)
    private String allowedModels;

    @Column(name = "prompt_code")
    private String promptCode;

    @Column(name = "output_schema_code")
    private String outputSchemaCode;

    @Column(name = "max_steps")
    private int maxSteps = 8; // default: 8

    private String temperature = "0.2"; // default: 0.2

    @Column(length = 1000)
    private String description;

}