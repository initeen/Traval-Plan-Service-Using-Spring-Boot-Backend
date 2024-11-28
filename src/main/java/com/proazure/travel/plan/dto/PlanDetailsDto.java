package com.proazure.travel.plan.dto;

import java.util.Date;

import com.proazure.travel.plan.model.PlanCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanDetailsDto {

	private Long planId;

	private String planName;

	private String planDescription;

	private Double planBudget;

	private PlanCategory planCategory;
	
	private Boolean status;

	private Integer createdBy;

	private Date createdOn;

	private Integer updatedBy;

	private Date updatedOn;
}
