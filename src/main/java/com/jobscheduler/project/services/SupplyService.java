package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.Supply;
import com.jobscheduler.project.repositories.SupplyRepository;

@Service
public class SupplyService {

	@Autowired
	private SupplyRepository repository;
	
	public List<Supply> findAll(){
		return repository.findAll();
	}
	
	public Supply findById(Long id) {
	 	Optional<Supply> supply =  repository.findById(id);
		return supply.get();
	}
}
