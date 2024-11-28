package com.proazure.travel.plan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.proazure.travel.plan.model.PlanCategory;

@RepositoryRestResource
public interface PlanCategoryRepository extends JpaRepository<PlanCategory, Integer> {

}
