package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.repositories.JobRepository;

@Service
public class JobService {

	@Autowired
	private JobRepository repository;
	
	public List<Job> findAll(){
		return repository.findAll();
	}
	
	public Job findById(Long id) {
	 	Optional<Job> obj =  repository.findById(id);
		return obj.get();
	}
	
	public List<Job> findByCategory(Category category){
		return repository.findByCategory(category);
	}
}
