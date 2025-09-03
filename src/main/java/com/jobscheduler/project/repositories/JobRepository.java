package com.jobscheduler.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Job;

public interface JobRepository extends JpaRepository<Job, Long> {
	List<Job> findByCategory(Category category);
}
