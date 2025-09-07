package com.jobscheduler.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.services.JobOrderService;
import com.jobscheduler.project.services.exceptions.ResourceNotFoundException;

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
	
	@GetMapping(value = "/user-name/{username}")
	public ResponseEntity<List<JobOrder>> findByUser(
			@RequestParam(required = false, name="vendor") String name){

		System.out.println("name received: " + name);
		
        List<JobOrder> list = service.findByUser(name);
        return ResponseEntity.ok().body(list);
	}
	
}
