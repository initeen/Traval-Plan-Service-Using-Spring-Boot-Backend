package com.proazure.travel.plan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proazure.travel.plan.dto.ActivePlansResponse;
import com.proazure.travel.plan.dto.PlanDetailsDto;
import com.proazure.travel.plan.service.PlanDetailsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/plan")
public class PlanDetailsController {

	@Autowired
	private PlanDetailsService planDetailsService;

	@PostMapping("/save")
	public ResponseEntity<?> savePlanDetails(@RequestBody PlanDetailsDto planDetailsDto) {

		Boolean savePlanDetails = planDetailsService.savePlanDetails(planDetailsDto);
		if (savePlanDetails) {

			return new ResponseEntity<>("Travel Plan Save Successfully!", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Travel Plan Not Saved!", HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/")
	public ResponseEntity<?> getAllPlans() {

		List<PlanDetailsDto> allPlans = planDetailsService.getAllPlans();
		if (CollectionUtils.isEmpty(allPlans)) {

			return ResponseEntity.notFound().build();
		} else {
			return new ResponseEntity<>(allPlans, HttpStatus.OK);
		}

	}

	@GetMapping("/active")
	public ResponseEntity<?> getActivePlans() {

		List<ActivePlansResponse> allActivePlans = planDetailsService.getActivePlans();
		if (CollectionUtils.isEmpty(allActivePlans)) {
			return ResponseEntity.notFound().build();
		} else {
			return new ResponseEntity<>(allActivePlans, HttpStatus.OK);
		}
	}

	@GetMapping("/{planId}")
	public ResponseEntity<?> getPlanDetailsById(@PathVariable Long planId) throws Exception {

		PlanDetailsDto planDetailsDto = planDetailsService.getPlanById(planId);

		if (ObjectUtils.isEmpty(planDetailsDto)) {

			return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(planDetailsDto, HttpStatus.OK);
	}

	@DeleteMapping("/{planId}")
	public ResponseEntity<?> deletePlanById(@PathVariable Long planId) throws Exception {

		Boolean delete = planDetailsService.deletePlan(planId);
		if (delete) {

			return new ResponseEntity<>("Plan Delete Successfully For Id: " + planId, HttpStatus.OK);
		}

		return new ResponseEntity<>("Plan Not Delete for Id: " + planId, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@PutMapping("/update/status/{planId}")
	public ResponseEntity<?> updatePlanStatus(@PathVariable Long planId) throws Exception {

		Boolean updated = planDetailsService.updatePlanStatus(planId);

		if (updated) {

			return new ResponseEntity<>("Plan status change successfull for Id: " + planId, HttpStatus.OK);
		}

		return new ResponseEntity<>("Failed to change status for Id: " + planId, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
