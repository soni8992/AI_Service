package com.jsp.ai_service.service.impl;

import com.jsp.ai_service.model.AgentDefinitionEntity;
import com.jsp.ai_service.repository.AgentAdminRepository;
import com.jsp.ai_service.requests.AgentDefinitionRequest;
import com.jsp.ai_service.service.AgentService;
import com.jsp.ai_service.util.DateUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgentAdminService implements AgentService {

    private AgentAdminRepository agentRepo;

    @Override
    public ResponseEntity<?> createDraft(String tenantId, AgentDefinitionRequest request) {

        // create the entity
        if(tenantId == null)
            return new ResponseEntity<>("No tenant id specified", HttpStatus.BAD_REQUEST);
        else if(!tenantId.equals(request.getTenantId()))
            return new ResponseEntity<>("Tenant IDs don't match!", HttpStatus.NOT_ACCEPTABLE);

        // check the value of max steps
        int maxSteps = request.getMaxSteps();

        if(maxSteps <= 0)
            return new ResponseEntity<>("Invalid value for max steps!", HttpStatus.valueOf("Invalid Request"));

        String reqTemp = request.getTemperature();
        Double temperature = null;

        try {

            temperature = Double.parseDouble(reqTemp);

        } catch(NumberFormatException e) {

            return new ResponseEntity<>("Temperature cannot be blank or non-numeric!", HttpStatus.NOT_ACCEPTABLE);

        }

        // store allowed models in a list
        List<String> allowedModelsList = Arrays.asList(request.getAllowedModels().split(",[\t\n ]+"));

        if(allowedModelsList.size() == 0)
            return new ResponseEntity<>("List of allowed models cannot be empty!", HttpStatus.NOT_ACCEPTABLE);

        String trimmedModelList = allowedModelsList.stream()
                .filter(model -> model.isEmpty())
                .map(model -> model.trim())
                .collect(Collectors.joining(", "));




        List<AgentDefinitionEntity> agents = agentRepo.findByTenantIdAgentCodeVersion(request.getTenantId(),
                request.getAgentCode(),
                request.getVersion());

        AgentDefinitionEntity savedAgent = null;

        if(agents.isEmpty()) {

            AgentDefinitionEntity agentDefinitionEntity = new AgentDefinitionEntity();
            agentDefinitionEntity.setTenantId(request.getTenantId());
            agentDefinitionEntity.setAgentCode(request.getAgentCode());
            agentDefinitionEntity.setAgentName(request.getAgentName());
            agentDefinitionEntity.setVersion(0);
            agentDefinitionEntity.setStatusFlag("DRAFT");
            agentDefinitionEntity.setModelProvider(request.getModelProvider());
            agentDefinitionEntity.setModelName(request.getModelName());
            agentDefinitionEntity.setAllowedModels(trimmedModelList);
            agentDefinitionEntity.setPromptCode(request.getPromptCode());
            agentDefinitionEntity.setOutputSchemaCode(request.getOutputSchemaCode());
            agentDefinitionEntity.setMaxSteps(maxSteps);
            agentDefinitionEntity.setTemperature(Double.toString(temperature));
            agentDefinitionEntity.setDescription(request.getDescription());

            // set the audit columns
            agentDefinitionEntity.setCreatedDate(DateUtil.toDate("2026-10-31"));
            agentDefinitionEntity.setCreatedBy("Priyanuj Bora");
            agentDefinitionEntity.setModifiedDate(DateUtil.toDate("2026-11-01"));
            agentDefinitionEntity.setModifiedBy("Claude AI");

            savedAgent = agentRepo.save(agentDefinitionEntity);

        }

        return new ResponseEntity<>("Successfully created new entry",
                savedAgent == null ? HttpStatus.valueOf("Agent already exists") : HttpStatus.OK);

    }

}