package com.proazure.travel.plan.service;

import java.util.List;

import com.proazure.travel.plan.dto.ActivePlansResponse;
import com.proazure.travel.plan.dto.PlanDetailsDto;

public interface PlanDetailsService {

	public Boolean savePlanDetails(PlanDetailsDto planDetailsDto);
	
	List<PlanDetailsDto> getAllPlans();

	public List<ActivePlansResponse> getActivePlans();

	public PlanDetailsDto getPlanById(Long planId) throws Exception;

	public Boolean deletePlan(Long planId) throws Exception;

	public Boolean updatePlanStatus(Long planId) throws Exception;
}
