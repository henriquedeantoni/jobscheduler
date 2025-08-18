package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.JobOrder;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long> {
	
}
