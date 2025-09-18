package com.jobscheduler.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jobscheduler.project.entities.Location;
import com.jobscheduler.project.services.LocationService;

@RestController
@RequestMapping(value = "/locations")
public class LocationResource {

	@Autowired
	private LocationService locationService;
	
	@GetMapping
	public ResponseEntity<List<Location>> findAll(){
		List<Location> list = locationService.findAll();
		
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Location> findById(@PathVariable Long id){
		Location location = locationService.findById(id);
		return ResponseEntity.ok().body(location);
	}
	
	@GetMapping(value="/state/{stateName}")
	public ResponseEntity<List<Location>> findByState(@PathVariable String stateName){
		List<Location> locations = locationService.findLocationByState(stateName);
		return ResponseEntity.ok(locations);
	}
	
	@GetMapping(value="/city/{cityName}")
	public ResponseEntity<List<Location>> findByCity(@PathVariable String cityName){
		List<Location> locations = locationService.findLocationByState(cityName);
		return ResponseEntity.ok(locations);
	}
	
	@GetMapping(value="/postalcode")
	public ResponseEntity<List<Location>> findByPostalCodeContaining(@RequestParam String segment){
		List<Location> locations = locationService.findLocationsByPostalCodeContaining(segment);
		return ResponseEntity.ok(locations);
	}
	
	@PostMapping
	public ResponseEntity<Location> insert(@RequestBody Location location){
		location = locationService.insert(location);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(location.getId()).toUri();
	
		return ResponseEntity.created(uri).body(location);
	}
}
