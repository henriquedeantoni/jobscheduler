package com.jobscheduler.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.services.JobService;

@RestController
@RequestMapping(value = "/job")
public class JobResource {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping
	public ResponseEntity<List<Job>> findAll(){
		List<Job> list = jobService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Job> findById(@PathVariable Long id){
		Job job = jobService.findById(id);
		return ResponseEntity.ok().body(job);
	}
	
	@GetMapping(value = "/{category}")
	public ResponseEntity<List<Job>> findByCategory(@PathVariable Category category){
		List<Job> list = jobService.findByCategory(category);
		return ResponseEntity.ok().body(list);
	}
}
