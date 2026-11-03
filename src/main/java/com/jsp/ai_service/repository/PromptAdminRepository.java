package com.jsp.ai_service.repository;

import com.jsp.ai_service.model.PromptTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface PromptAdminRepository extends JpaRepository<PromptTemplateEntity, BigInteger> {

    @Query(value = "from PromptTemplateEntity where tenantId=:tenantId and promptCode=:promptCode and version=:version")
    List<PromptTemplateEntity> findByTenantIdPromptCodeVersion(String tenantId, String promptCode, Integer version);

}