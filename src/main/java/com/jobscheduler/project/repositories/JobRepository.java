package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	
}
