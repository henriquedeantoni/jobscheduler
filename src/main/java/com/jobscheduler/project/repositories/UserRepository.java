package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
