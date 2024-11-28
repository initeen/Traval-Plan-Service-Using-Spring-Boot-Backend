package com.proazure.travel.plan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plan_details")
public class PlanDetails extends BaseModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "plan_id")
	private Long planId;

	@NotBlank(message = "Plan name is required")
	@Size(min = 2, max = 100, message = "Plan name must be between 2 and 100 characters")
	@Column(name = "plan_name", nullable = false)
	private String planName;

	@NotBlank(message = "Plan description is required")
	@Size(min = 10, max = 500, message = "Plan description must be between 10 and 500 characters")
	@Column(name = "plan_description", nullable = false)
	private String planDescription;

	@NotNull(message = "Plan budget is required")
	@DecimalMin(value = "0.0", inclusive = false, message = "Plan budget must be greater than 0")
	@Column(name = "plan_budget", nullable = false)
	private Double planBudget;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private PlanCategory planCategory;
	
}
