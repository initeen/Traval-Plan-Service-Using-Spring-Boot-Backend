package com.proazure.travel.plan.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseModel {

	private Boolean status;
	
	@NotNull(message = "Created by is required")
	@Column(name = "created_by", nullable = false)
	private Integer createdBy;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private Integer updatedBy;

	@Column(name = "updated_on")
	private Date updatedOn;
	
}
