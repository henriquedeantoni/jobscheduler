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
import com.jobscheduler.project.services.JobService;

@RestController
@RequestMapping(value = "/jobs")
public class JobResource {
	
	@Autowired
	private JobService jobService;
	
	@GetMapping(value = "/search")
	public ResponseEntity<List<Job>> findAll(
		@RequestParam(required = false) List<String> categories)  {

		System.out.println("Categories recebidas no controller: " + categories);
		
		List<Job> list;
        if (categories == null || categories.isEmpty()) {
            list = jobService.findAll(null); 
        } else {
            list = jobService.findAll(categories);
        }
	    return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Job> findById(@PathVariable Long id){
		Job job = jobService.findById(id);
		return ResponseEntity.ok().body(job);
	}
}
