package com.jsp.ai_service.service;

import com.jsp.ai_service.requests.OutputSchemaRequest;

public interface SchemaService {

    void createDraft(String tenantId, OutputSchemaRequest request);

}