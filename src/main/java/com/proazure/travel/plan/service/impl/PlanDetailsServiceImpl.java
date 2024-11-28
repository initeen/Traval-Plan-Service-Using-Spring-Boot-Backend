package com.proazure.travel.plan.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.proazure.travel.plan.dto.ActivePlansResponse;
import com.proazure.travel.plan.dto.PlanDetailsDto;
import com.proazure.travel.plan.model.PlanDetails;
import com.proazure.travel.plan.repository.PlanDetailsRepository;
import com.proazure.travel.plan.service.PlanDetailsService;
import com.proazure.travel.plan.util.Validation;

@Service
public class PlanDetailsServiceImpl implements PlanDetailsService {

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PlanDetailsRepository planDetailsRepository;

	@Autowired
	private Validation validation;
	
	@Override
	public Boolean savePlanDetails(PlanDetailsDto planDetailsDto) {
		
		//validation checking
		validation.planDetailsValidation(planDetailsDto);
		
		PlanDetails planDetails = mapper.map(planDetailsDto, PlanDetails.class);
		
		if(ObjectUtils.isEmpty(planDetails.getPlanId())) {
			
			planDetails.setCreatedBy(1);
			planDetails.setCreatedOn(new Date());
			planDetails.setStatus(true);
			
		}else {
			updatePlanDetails(planDetails);
		}
		
		PlanDetails savePlan = planDetailsRepository.save(planDetails);
		
		if(ObjectUtils.isEmpty(savePlan)) {
			
			return false;
		}
		
		return true;
	}

	private void updatePlanDetails(PlanDetails planDetails) {
		
		Optional<PlanDetails> findByPlanId = planDetailsRepository.findById(planDetails.getPlanId());
		
		if(findByPlanId.isPresent()) {
			PlanDetails existPlanDetails = findByPlanId.get();
			
			planDetails.setCreatedBy(existPlanDetails.getCreatedBy());
			planDetails.setCreatedOn(existPlanDetails.getCreatedOn());
			
			planDetails.setUpdatedBy(1);
			planDetails.setUpdatedOn(new Date());
			//planDetails.setStatus(true);
		}
	}

	@Override
	public List<PlanDetailsDto> getAllPlans() {
		
		List<PlanDetails> plans = planDetailsRepository.findAll();
		List<PlanDetailsDto> planDtoList = plans.stream().map(plan -> mapper.map(plan, PlanDetailsDto.class)).toList();
		return planDtoList;
	}

	@Override
	public List<ActivePlansResponse> getActivePlans() {
		
		List<PlanDetails> plans = planDetailsRepository.findByStatusTrue();
		
		List<ActivePlansResponse> activePlans = plans.stream().map(plan -> mapper.map(plan, ActivePlansResponse.class)).toList();
		
		return activePlans;
	}

	@Override
	public PlanDetailsDto getPlanById(Long planId) throws Exception {
		
		PlanDetails plan = planDetailsRepository.findById(planId)
				.orElseThrow(()-> new ResourceNotFoundException("Plan not found for id:"+planId));
		if(!ObjectUtils.isEmpty(plan)) {
			
			//PlanDetails planDetails = findPlan.get();
			return mapper.map(plan, PlanDetailsDto.class);
		}
		return null;
	}

	@Override
	public Boolean deletePlan(Long planId) throws Exception  {
		 PlanDetails plan = planDetailsRepository.findById(planId)
				 .orElseThrow(()-> new ResourceNotFoundException("Plan not found for id:"+planId));
		 if(!ObjectUtils.isEmpty(plan)) {
			 
			 planDetailsRepository.deleteById(planId);
			 return true;
		 }
		return false;
	}

	@Override
	public Boolean updatePlanStatus(Long planId) throws Exception {
		PlanDetails plan = planDetailsRepository.findById(planId)
				.orElseThrow(()-> new ResourceNotFoundException("Plan not found for id:"+planId));
		if(!ObjectUtils.isEmpty(plan)) {
			//PlanDetails existingPlan = plan.get();
			
			Boolean currentStatus = plan.getStatus();
			System.out.println(currentStatus);
			plan.setStatus(!currentStatus);
			planDetailsRepository.save(plan);
			
			return true;
		}
		return false;
	}
	
	
}
