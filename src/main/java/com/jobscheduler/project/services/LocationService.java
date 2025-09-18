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
	
	public List<Location> findLocationByState(String stateName){
		return locationRepository.findByState(stateName);
	}
	
	public List<Location> findLocationsByPostalCodeContaining(String segment){
		if(segment.matches("\\d")) {
			return locationRepository.findByPostalCodeContainingIgnoreCase(segment);
		} else {
			throw new IllegalArgumentException("Postal code must have only digits");
		}
	}
	
	public List<Location> findLocationsByCity(String cityName){
		return locationRepository.findByCity(cityName);
	}
}
