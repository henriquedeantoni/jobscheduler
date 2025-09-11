package com.jobscheduler.project.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jobscheduler.project.entities.Client;
import com.jobscheduler.project.repositories.ClientRepository;
import com.jobscheduler.project.services.exceptions.DatabaseException;
import com.jobscheduler.project.services.exceptions.ResourceNotFoundException;


@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;
	
	public List<Client> findAll(){
		return repository.findAll();
	}
	
	public Client findById(Long id) {
	 	Optional<Client> obj =  repository.findById(id);
		return obj.get();
	}
	
	public Client insert(Client obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch(DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public List<Client> findClientByContains(String likeName){
		System.out.println("partialName on services: " +  likeName);
		return repository.findByNameContainingIgnoreCase(likeName);
	}
}
