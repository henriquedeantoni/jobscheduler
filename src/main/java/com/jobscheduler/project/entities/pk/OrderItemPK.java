package com.jobscheduler.project.entities.pk;

import java.io.Serializable;

import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.entities.JobOrder;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OrderItemPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private JobOrder jobOrder;

	@ManyToOne
	@JoinColumn(name = "job_id")	
	private Job job;
	
	public OrderItemPK() {
	}
	
	public OrderItemPK(JobOrder jobOrder, Job job) {
		super();
		this.jobOrder = jobOrder;
		this.job = job;
	}

	public JobOrder getJobOrder() {
		return jobOrder;
	}
	public void setJobOrder(JobOrder jobOrder) {
		this.jobOrder = jobOrder;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((job == null) ? 0 : job.hashCode());
		result = prime * result + ((jobOrder == null) ? 0 : jobOrder.hashCode());
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
		OrderItemPK other = (OrderItemPK) obj;
		if (job == null) {
			if (other.job != null)
				return false;
		} else if (!job.equals(other.job))
			return false;
		if (jobOrder == null) {
			if (other.jobOrder != null)
				return false;
		} else if (!jobOrder.equals(other.jobOrder))
			return false;
		return true;
	}
}
