package com.jobscheduler.project.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByName(String name);
	List<Client> findByNameContainingIgnoreCase(String partialName);
	List<Client> findBySsnNumberContainingIgnoreCase(String ssn);
	List<Client> findByTinNumberContainingIgnoreCase(String tin);
}
