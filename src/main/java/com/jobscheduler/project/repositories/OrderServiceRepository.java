package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.OrderService;

public interface OrderServiceRepository extends JpaRepository<OrderService, Long> {
	
}
