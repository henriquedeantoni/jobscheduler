package com.jobscheduler.project.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.jobscheduler.project.entities.Job;

import jakarta.persistence.criteria.Join;

public class JobSpecifications {
	
	public static Specification<Job> hasCategory(String category) {
		return (root, query, cb) ->
		{
			if(category == null) return null;
			Join<Object, Object> join = root.join("categories");
			return cb.equal(join.get("name"), category);
		};
	}
}
