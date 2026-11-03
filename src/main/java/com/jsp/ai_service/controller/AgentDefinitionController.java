package com.jsp.ai_service.controller;

import com.jsp.ai_service.model.AgentDefinitionEntity;
import com.jsp.ai_service.model.OutputSchemaEntity;
import com.jsp.ai_service.model.PromptTemplateEntity;
import com.jsp.ai_service.requests.AgentDefinitionRequest;
import com.jsp.ai_service.service.AgentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agent-controller")
public class AgentDefinitionController {

    private AgentService agentService;

    /*
    @GetMapping(value = "/greetings")
    public String greet() {
        return "Hello from Ai_service Eureka client!";
    }
     */

    @PostMapping(value = "/api/ai/v1/agents/{tenantId}")
    public ResponseEntity<?> createAgent(@PathVariable String tenantId, @RequestBody AgentDefinitionRequest request) {

        return agentService.createDraft(tenantId, request);

    }

    /*
    @PostMapping(value = "/api/ai/v1/schemas/{tenantId}")
    public ResponseEntity<?> createSchema(@PathVariable String tenantId, @RequestBody OutputSchemaEntity aiSchema) {
    }
     */

}