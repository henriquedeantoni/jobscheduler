package com.jobscheduler.project.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.jobscheduler.project.entities.Job;

public class JobSpecifications {
	
	public static Specification<Job> hasCategory(String category) {
		return (root, query, cb) ->
			category == null ? null : cb.equal(root.get("category"), category);
	}
}
