package com.jobscheduler.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.repositories.UserRepository;

public class UserServices {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
}
