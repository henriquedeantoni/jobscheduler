package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
