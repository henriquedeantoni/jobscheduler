package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.OrderSupply;
import com.jobscheduler.project.entities.pk.OrderSupplyPK;

public interface OrderSupplyRepository extends JpaRepository<OrderSupply, OrderSupplyPK> {

}
