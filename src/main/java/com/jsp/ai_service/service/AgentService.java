package com.jsp.ai_service.service;

import com.jsp.ai_service.requests.AgentDefinitionRequest;
import org.springframework.http.ResponseEntity;

public interface AgentService {

    ResponseEntity<?> createDraft(String tenantId, AgentDefinitionRequest request);

}