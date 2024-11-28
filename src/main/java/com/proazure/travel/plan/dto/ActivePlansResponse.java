package com.proazure.travel.plan.dto;

import com.proazure.travel.plan.model.PlanCategory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivePlansResponse {

	private Long planId;

	private String planName;

	private String planDescription;

	private Double planBudget;

	private PlanCategory planCategory;
	
}
