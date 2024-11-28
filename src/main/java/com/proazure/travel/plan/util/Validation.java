package com.proazure.travel.plan.util;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.proazure.travel.plan.dto.PlanDetailsDto;
import com.proazure.travel.plan.exception.ValidationException;

@Component
public class Validation {

	public void planDetailsValidation(PlanDetailsDto planDetailsDto) {
		
		Map<String, Object> errors = new LinkedHashMap<>();
		
		if(ObjectUtils.isEmpty(planDetailsDto)) {
			
			throw new IllegalArgumentException("Plan details object/JSON should not be zero or empty");
		} else {
			
			//validation for planName field
			if(ObjectUtils.isEmpty(planDetailsDto.getPlanName())) {
				 
				errors.put("planName", "Plan name is empty or null!!");
			} else {
				if (planDetailsDto.getPlanName().length() < 2) {
					
					errors.put("planName", "Plan name required mini. 2 character");
				}
				if (planDetailsDto.getPlanName().length() > 100) {
					
					errors.put("planName", "Plan name required less than 100 character");
				}
			}
			
			//validation for planDescription field
			if(ObjectUtils.isEmpty(planDetailsDto.getPlanDescription())) {
				
				errors.put("planDescription", "Plan description is empty or null!!");
			} else {
				if (planDetailsDto.getPlanDescription().length() < 10) {
					
					errors.put("planDescription", "Plan description required mini. 10 character");
				}
				if (planDetailsDto.getPlanDescription().length() > 500) {
					
					errors.put("planDescription", "Plan description required less than 500 character");
				}
			}
			
			// validation for planBudget
			if(ObjectUtils.isEmpty(planDetailsDto.getPlanBudget())){
				
				errors.put("planBudget", "Plan budget is empty or null!!");
			}
			
			//validation for planCategory
			if(ObjectUtils.isEmpty(planDetailsDto.getPlanCategory())) {
				
				errors.put("planCategory", "Plan category is empty or null!!");
			}
			
			//validation for createdBy field
//			if(ObjectUtils.isEmpty(planDetailsDto.getCreatedBy())) {
//				
//				errors.put("createdBy", "created by is empty or null!!");
//			}
			
		}
		
		if(!errors.isEmpty()) {
			throw new ValidationException(errors);
		}
	}
}
