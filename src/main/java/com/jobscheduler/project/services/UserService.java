package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.entities.enums.UserStatus;
import com.jobscheduler.project.repositories.UserRepository;
import com.jobscheduler.project.services.exceptions.DatabaseException;
import com.jobscheduler.project.services.exceptions.InactiveUserException;
import com.jobscheduler.project.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;


@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
	 	Optional<User> obj =  repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);	
		} catch (EmptyResultDataAccessException e){
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}	
	
	/*
	 * 
	public void delete(Long id) {
		try {
		repository.deleteById(id);	
		} catch (RuntimeException e){
			e.printStackTrace();
		}
	}
	 * 
	 */
	
	public User update(Long id, User user) {
		try {
			User entity = repository.getReferenceById(id);
			if(entity.getUserStatus() != UserStatus.INACTIVE) {
				updateData(entity, user);				
				return repository.save(entity);			
			} else {
				throw new InactiveUserException("User is inactive, update is not allowed.");
			}
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
	
	private void updateData(User entity, User user) {
		entity.setName(user.getName());
		entity.setEmail(user.getEmail());
		entity.setPhone(user.getPhone());
	}
	
	public User changeStatus(UserStatus userStatus, Long id) {
		try {
			User entity = repository.getReferenceById(id);
			if(entity.getUserStatus() != UserStatus.INACTIVE) {
				entity.setUserStatus(userStatus);
			}
			return repository.save(entity);
		} catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(e.getMessage());
		}
	}
}
