package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Supply;

public interface SupplyRepository extends JpaRepository<Supply, Long> {
}
