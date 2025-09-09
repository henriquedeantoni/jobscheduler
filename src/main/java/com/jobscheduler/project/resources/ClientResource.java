package com.jobscheduler.project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jobscheduler.project.entities.Client;
import com.jobscheduler.project.services.ClientService;

@RestController
@RequestMapping(value="/client")
public class ClientResource {

	@Autowired
	private ClientService clientService;
	
	@GetMapping
	public ResponseEntity<List<Client>> findAll(){
		List<Client> clients = clientService.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id){
		Client client = clientService.findById(id);
		return ResponseEntity.ok().body(client);
	}
	
	@PostMapping
	public ResponseEntity<Client> insert(@RequestBody Client client){
		client = clientService.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(client.getId()).toUri();
		return ResponseEntity.created(uri).body(client);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Client> delete(@PathVariable Long id){
		clientService.delete(id);
		return ResponseEntity.noContent().build();
	}
}

