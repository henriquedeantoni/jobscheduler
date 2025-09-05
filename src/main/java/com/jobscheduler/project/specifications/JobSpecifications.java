package com.jobscheduler.project.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jobscheduler.project.entities.Job;

import jakarta.persistence.criteria.Join;

public class JobSpecifications {
	
	public static Specification<Job> hasCategories(List<String> categories) {
		return (root, query, cb) ->
		{
			if(categories == null || categories.isEmpty()) return null;
			Join<Object, Object> join = root.join("categories");
			return join.get("name").in(categories);
		};
	}
}
