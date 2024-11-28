package com.proazure.travel.plan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proazure.travel.plan.model.PlanDetails;

@RepositoryRestResource
public interface PlanDetailsRepository extends JpaRepository<PlanDetails, Long> {

	List<PlanDetails> findByStatusTrue();

}
