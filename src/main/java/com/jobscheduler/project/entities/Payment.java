package com.jobscheduler.project.entities;

import java.time.Instant;

public class Payment {
	private Long id;
	private Instant moment;
	
	private JobOrder jobOrder;
	
	private Payment payment;
	
}
