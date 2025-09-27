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

	public Client findByName(String name) {
	 	Client obj =  repository.findByName(name);
		return obj;
	}
	
	public Client insert(Client obj) {
		
	    if (obj == null) {
	        throw new IllegalArgumentException("client cannot be null.");
	    }

	    if (obj.getName() == null || obj.getName().trim().isEmpty()) {
	        throw new IllegalArgumentException("client name cannot be empty.");
	    }

	    String ssn = obj.getSsnNumber();
	    String tin = obj.getTinNumber();

	    if ((ssn == null || ssn.isEmpty()) && (tin == null || tin.isEmpty())) {
	        throw new IllegalArgumentException("client must have either ssn number or tin number.");
	    }

	    if (ssn != null && !ssn.isEmpty() && tin != null && !tin.isEmpty()) {
	        throw new IllegalArgumentException("client must not have both ssn number and tin number.");
	    }

	    if (findByName(obj.getName()) != null) {
	        throw new IllegalArgumentException("client already exists.");
	    }

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
		return repository.findByNameContainingIgnoreCase(likeName);
	}
	
	public List<Client> findClientBySsnNumberContains(String segmentSsn){
		if(!segmentSsn.matches("\\d")) {
			return repository.findBySsnNumberContainingIgnoreCase(segmentSsn);
		} else {
			throw new IllegalArgumentException("SSN number must have only digits");
		}
	}
	
	public List<Client> findClientByTinNumberContains(String segmentTin){
		if(!segmentTin.matches("\\d")) {
			return repository.findBySsnNumberContainingIgnoreCase(segmentTin);
		} else {
			throw new IllegalArgumentException("TIN number must have only digits");
		}
	}
}
