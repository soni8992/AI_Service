package com.jsp.ai_service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Clob;

@Data
@Entity
@Table(name = "ai_prompt_template")
public class PromptTemplateEntity extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alt_key")
    private BigInteger altKey;

    @Column(name = "tenant_id", nullable = false, unique = true)
    private String tenantId;

    @Column(name = "prompt_code", nullable = false)
    private String promptCode;

    @Column(unique = true)
    private int version;

    @Column(name = "status_flag")
    private String statusFlag;

    @Lob
    @Column(name = "system_prompt")
    private Clob systemPrompt;

    @Lob
    @Column(name = "user_prompt_template")
    private Clob userPromptTemplate;

}