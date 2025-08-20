package com.jobscheduler.project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobscheduler.project.entities.Supply;
import com.jobscheduler.project.services.SupplyService;

@RestController
@RequestMapping(value = "/supplies")
public class SupplyResource {
	
	@Autowired
	private SupplyService service;
	
	
	@GetMapping
	public ResponseEntity<List<Supply>> findAll(){
		List<Supply> supplyList = service.findAll();
		return ResponseEntity.ok().body(supplyList);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Supply> findById(@PathVariable Long id){
		Supply supply = service.findById(id);
		return ResponseEntity.ok().body(supply);
	}
	
}
