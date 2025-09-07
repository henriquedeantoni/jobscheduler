package com.jobscheduler.project.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.jobscheduler.project.entities.JobOrder;

public class JobOrderSpecifications {
	
	public static Specification<JobOrder> hasUsername(String username) {
		return (root, query, cb) ->
			username == null ? null : cb.equal(root.get("vendor").get("name"), username);
	}
}
