package com.jsp.ai_service.repository;

import com.jsp.ai_service.model.AgentDefinitionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface AgentAdminRepository extends JpaRepository<AgentDefinitionEntity, BigInteger> {

    @Query(value = "from AgentDefinitionEntity where tenantId=:tenantId and agentCode=:agentCode and version=:version")
    List<AgentDefinitionEntity> findByTenantIdAgentCodeVersion(String tenantId, String agentCode, Integer version);

}