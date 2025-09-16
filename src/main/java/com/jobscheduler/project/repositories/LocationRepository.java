package com.jobscheduler.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Client;
import com.jobscheduler.project.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	List<Location> findByState(String state);
	
	List<Location> findByCity(String city);
}
