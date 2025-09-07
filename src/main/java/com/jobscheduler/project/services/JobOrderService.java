package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.repositories.JobOrderRepository;
import com.jobscheduler.project.specifications.JobOrderSpecifications;


@Service
public class JobOrderService {

	@Autowired
	private JobOrderRepository repository;
	
	public List<JobOrder> findAll(){
		return repository.findAll();
	}
	
	public JobOrder findById(Long id) {
	 	Optional<JobOrder> obj =  repository.findById(id);
		return obj.get();
	}
	
	public List<JobOrder> findByUser(String username){
		if(username==null || username.isEmpty()) {
			return null;
		}
		
		return repository.findAll(Specification.allOf(JobOrderSpecifications.hasUsername(username)));
	}
}
