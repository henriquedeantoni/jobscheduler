package com.jobscheduler.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.services.JobOrderService;

@RestController
@RequestMapping(value = "/job-orders")
public class JobOrderResource {
	
	@Autowired
	private JobOrderService service;
	
	
	@GetMapping
	public ResponseEntity<List<JobOrder>> findAll(){
		List<JobOrder> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<JobOrder> findById(@PathVariable Long id){
		JobOrder obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
