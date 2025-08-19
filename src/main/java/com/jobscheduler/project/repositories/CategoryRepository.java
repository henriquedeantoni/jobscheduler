package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
}
