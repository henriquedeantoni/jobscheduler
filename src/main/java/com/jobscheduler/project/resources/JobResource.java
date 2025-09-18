package com.jobscheduler.project.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.services.JobService;

@RestController
@RequestMapping(value = "/jobs")
public class JobResource {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping
	public ResponseEntity<List<Job>> findAll(
		@RequestParam(required = false, name = "categories") String categoriesParam,  
		@RequestParam(required = false, defaultValue = "any") String match) {

	        List<String> categories = null;
	        if (categoriesParam != null && !categoriesParam.isEmpty()) {
	            categories = Arrays.stream(categoriesParam.split(","))
	                               .map(String::trim)
	                               .filter(s -> !s.isEmpty())
	                               .toList();
	        }

	        List<Job> list;
	        if ("all".equalsIgnoreCase(match)) {
	            list = jobService.findAllWithAllCategories(categories); //AND
	        } else {
	            list = jobService.findAll(categories); //OR
	        }

	        return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Job> findById(@PathVariable Long id){
		Job job = jobService.findById(id);
		return ResponseEntity.ok().body(job);
	}
	
	@GetMapping(value = "/duration/greater={value}")
	public ResponseEntity<List<Job>> findByDurationGreaterThan(@PathVariable Integer value){
		List<Job> jobs = jobService.findJobByDurationGreaterThan(value);
		return ResponseEntity.ok(jobs);
	}
	
	@GetMapping(value = "/duration/lower={value}")
	public ResponseEntity<List<Job>> findByDurationLessThan(@PathVariable Integer value){
		List<Job> jobs = jobService.findJobByDurationLessThan(value);
		return ResponseEntity.ok(jobs);
	}
}
