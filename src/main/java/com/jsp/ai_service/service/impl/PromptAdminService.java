package com.jsp.ai_service.service.impl;

import com.jsp.ai_service.model.PromptTemplateEntity;
import com.jsp.ai_service.repository.PromptAdminRepository;
import com.jsp.ai_service.requests.PromptTemplateRequest;
import com.jsp.ai_service.service.PromptService;
import com.jsp.ai_service.util.DateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PromptAdminService implements PromptService {

    private PromptAdminRepository promptRepo;

    @Override
    public ResponseEntity<?> createDraft(String tenantId, PromptTemplateRequest request) {

        if(tenantId == null)
            return new ResponseEntity<>("Tenant Id cannot be null", HttpStatus.valueOf("No tenant id passed"));

        else if(!tenantId.equals(request.getTenantId()))
            return new ResponseEntity<>("Tenant Id should match!", HttpStatus.NOT_ACCEPTABLE);

        if(request.getVersion() < 0)
            return new ResponseEntity<>("Invalid version!", HttpStatus.NOT_ACCEPTABLE);

        List<PromptTemplateEntity> prompts = promptRepo.findByTenantIdPromptCodeVersion(tenantId,
                request.getPromptCode(),
                request.getVersion());

        PromptTemplateEntity savedPrompt = null;

        if(prompts.isEmpty()) {

            PromptTemplateEntity newPrompt = new PromptTemplateEntity();

            newPrompt.setTenantId(request.getTenantId());
            newPrompt.setPromptCode(request.getPromptCode());
            newPrompt.setStatusFlag("DRAFT");
            newPrompt.setVersion(0);
            newPrompt.setSystemPrompt(request.getSystemPrompt());
            newPrompt.setUserPromptTemplate(request.getUserPromptTemplate());

            // set the audit columns
            newPrompt.setCreatedBy("ChatGPT");
            newPrompt.setCreatedDate(DateUtil.toDate("2026-10-15"));
            newPrompt.setModifiedBy("Gemini");
            newPrompt.setModifiedDate(DateUtil.toDate("2026-11-01"));

            savedPrompt = promptRepo.save(newPrompt);

        }

        return new ResponseEntity<>(savedPrompt,
                savedPrompt == null ? HttpStatus.valueOf("Prompt already exists") : HttpStatus.CREATED);

    }

}