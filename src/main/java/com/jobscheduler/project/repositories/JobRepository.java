package com.jobscheduler.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long>, JpaSpecificationExecutor<Job> {
	List<Job> findByDurationGreaterThan(Integer minDuration);	
	List<Job> findByDurationLessThan(Integer maxDuration);
}
