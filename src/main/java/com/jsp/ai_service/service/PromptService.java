package com.jsp.ai_service.service;

import com.jsp.ai_service.requests.PromptTemplateRequest;
import org.springframework.http.ResponseEntity;

public interface PromptService {

    ResponseEntity<?> createDraft(String tenantId, PromptTemplateRequest request);

}