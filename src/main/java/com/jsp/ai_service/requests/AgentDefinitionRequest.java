package com.jsp.ai_service.requests;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class AgentDefinitionRequest {

    private String tenantId;

    private String agentCode;

    private String agentName;

    private int version;

    private String statusFlag;

    private String modelProvider;

    private String modelName;

    private String allowedModels;

    private String promptCode;

    private String outputSchemaCode;

    private int maxSteps = 8; // default: 8

    private String temperature = "0.2"; // default: 0.2

    private String description;

}