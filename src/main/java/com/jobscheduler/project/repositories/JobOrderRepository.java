package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.entities.JobOrder;

public interface JobOrderRepository extends JpaRepository<JobOrder, Long>,
											JpaSpecificationExecutor<JobOrder> {
}
