package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.repositories.JobRepository;
import com.jobscheduler.project.specifications.JobSpecifications;

@Service
public class JobService {

	@Autowired
	private JobRepository repository;
	
	public List<Job> findAll(List<String> categories){
		
		System.out.println("Categories recebidas: " + categories);
		
        if (categories == null || categories.isEmpty()) {
            return repository.findAll();
        }
		
	    return repository.findAll(
	            Specification.allOf(JobSpecifications.hasCategories(categories))
	        );
	}
	
	public List<Job> findAllWithAllCategories(List<String> categories){
		if(categories==null || categories.isEmpty()) {
			return repository.findAll();
		}
		
		return repository.findAll(Specification.allOf(JobSpecifications.hasAllCategories(categories)));				
	}

	public List<Job> findJobByDurationGreaterThan(Integer minDuration){
		if(minDuration == null || minDuration < 0) {
			throw new IllegalArgumentException("Duration value must be greather than zero.");
		}
		return repository.findByDurationGreaterThan(minDuration);
	}
	
	public List<Job> findJobByDurationLessThan(Integer maxDuration){
		if(maxDuration == null || maxDuration < 0) {
			throw new IllegalArgumentException("Duration value must be lower than zero.");
		}
		return repository.findByDurationLessThan(maxDuration);
	}
	
	public Job findById(Long id) {
	 	Optional<Job> obj =  repository.findById(id);
		return obj.get();
	}
}
