package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.Location;
import com.jobscheduler.project.repositories.LocationRepository;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public List<Location> findAll(){
		return locationRepository.findAll();
	}
	
	public Location findById(Long id) {
		Optional<Location> location = locationRepository.findById(id);
		return location.get();
	}
	
	public Location insert(Location location) {
		return locationRepository.save(location);
	}
	
	
}
