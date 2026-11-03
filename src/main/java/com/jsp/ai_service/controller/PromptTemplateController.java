package com.jsp.ai_service.controller;

import com.jsp.ai_service.requests.PromptTemplateRequest;
import com.jsp.ai_service.service.PromptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prompt-controller")
public class PromptTemplateController {

    private PromptService promptService;

    @PostMapping(value = "/api/ai/v1/prompts/{tenantId}")
    public ResponseEntity<?> createPrompt(@PathVariable String tenantId, @RequestBody PromptTemplateRequest request) {

        return promptService.createDraft(tenantId, request);

    }

}