package com.jobscheduler.project.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jobscheduler.project.entities.pk.OrderSupplyPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_supply")
public class OrderSupply implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrderSupplyPK id = new OrderSupplyPK();
	
	private Integer quantity;
	private BigDecimal price;
	
	public OrderSupply() {
	}

	public OrderSupply(JobOrder jobOrder, Supply supply, Integer quantity, BigDecimal price) {
		super();
		id.setJobOrder(jobOrder);
		id.setSupply(supply);
		this.quantity = quantity;
		this.price = price;
	}
	
	@JsonIgnore
	public JobOrder getJobOrder() {
		return id.getJobOrder();
	}
	
	public void setJobOrder(JobOrder jobOrder) {
		id.setJobOrder(jobOrder);
	}
	
	public Supply getSupply() {
		return id.getSupply();
	}

	public void setSupply(Supply supply) {
		id.setSupply(supply);
	}
	
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal getSubTotal() {
		return price.multiply(new BigDecimal(quantity));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		OrderSupply other = (OrderSupply) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
