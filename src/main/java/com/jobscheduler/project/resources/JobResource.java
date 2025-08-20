package com.jobscheduler.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.JobOrder;

@RestController
@RequestMapping(value = "/job")
public class JobResource {
	
	@Autowired
	private JobService service;
	
	
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
