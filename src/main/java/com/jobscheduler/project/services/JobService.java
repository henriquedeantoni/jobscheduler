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
	
	public Job findById(Long id) {
	 	Optional<Job> obj =  repository.findById(id);
		return obj.get();
	}
}
