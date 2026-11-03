package com.jsp.ai_service.requests;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;

@Data
public class OutputSchemaRequest {

    private String tenantId;

    private String schemaCode;

    private int version;

    private String statusFlag;

    private String jsonSchema;

}