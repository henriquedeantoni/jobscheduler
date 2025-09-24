package com.jobscheduler.project.entities.pk;

import java.io.Serializable;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.entities.Supply;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderSupplyPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private JobOrder jobOrder;

	@ManyToOne
	@JoinColumn(name = "supply_id")	
	private Supply supply;
	
	public OrderSupplyPK() {
	}
	
	public OrderSupplyPK(JobOrder jobOrder, Supply supply) {
		super();
		this.jobOrder = jobOrder;
		this.supply = supply;
	}

	public JobOrder getJobOrder() {
		return jobOrder;
	}
	public void setJobOrder(JobOrder jobOrder) {
		this.jobOrder = jobOrder;
	}
	public Supply getSupply() {
		return supply;
	}
	public void setSupply(Supply supply) {
		this.supply = supply;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobOrder == null) ? 0 : jobOrder.hashCode());
		result = prime * result + ((supply == null) ? 0 : supply.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderSupplyPK other = (OrderSupplyPK) obj;
		if (jobOrder == null) {
			if (other.jobOrder != null)
				return false;
		} else if (!jobOrder.equals(other.jobOrder))
			return false;
		if (supply == null) {
			if (other.supply != null)
				return false;
		} else if (!supply.equals(other.supply))
			return false;
		return true;
	}	
}
