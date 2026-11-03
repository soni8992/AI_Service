package com.jsp.ai_service.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.Temporal;

import java.util.Date;

@MappedSuperclass
@Data
public class AuditModel {

    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;

}