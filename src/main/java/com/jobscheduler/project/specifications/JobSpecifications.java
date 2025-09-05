package com.jobscheduler.project.specifications;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Job;

import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.SetJoin;

public class JobSpecifications {
	
	public static Specification<Job> hasCategories(List<String> categories) {
		return (root, query, cb) ->
		{
			if(categories == null || categories.isEmpty()) return null;
			
			query.distinct(true);
			SetJoin<Job, Category> join = root.joinSet("categories");
			
	        Expression<String> categoryNameUpper = cb.upper(join.get("name"));
	        List<String> categoriesUpper = categories.stream()
	                                                 .map(String::toUpperCase)
	                                                 .toList();
	        return categoryNameUpper.in(categoriesUpper);
		};
	}
	
	public static Specification<Job> hasAllCategories(List<String> categories) {
		return (root, query, cb) ->{
			if (categories == null || categories.isEmpty()) return null;
			
			query.distinct(true);
			
			SetJoin<Job, Category> join = root.joinSet("categories");
			
			Predicate predicate = cb.upper(join.get("name"))
									.in(categories.stream()
												.map(String::toUpperCase)
												.toList());
									
			query.groupBy(root.get("id"));
			
			query.having(cb.equal(cb.countDistinct(join.get("name")),categories.size()));
			
			return predicate;
		};
	}
}
