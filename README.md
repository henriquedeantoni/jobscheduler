## JOB SCHEDULER (A SPRING BOOT PROJECT CASE)


### USER RESOURCE

* Controller:  a classe UserResource é uma controller, ela é encarregada de criar os endpoints da Api. A parte inicial @Autowired é encarregada de efetuar a injeção de dependência, da camada de service para dentro da classe controller. Note que o @GetMapping são os mapeamentos dos métodos do tipo GET das requisições, a primeira é um findAll() e a segunda um findById(). 

```
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}

```

* Service: A classe UserService trata da camada de negócios do Usuário, consiste de dois métodos findAll() que lista todos os usuários e findById() que retorna um usuário através do seu id.
 
```
@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
	 	Optional<User> obj =  repository.findById(id);
		return obj.get();
	}
}
```

* Repository: A interface UserRepository é encarregada do acesso aos repositórios, ou, camada de acessos de dados. No Spring Data JPA, os repositórios são interfaces uma vez que o framework cria a implementação automática em tempo de execução. Isso significa que o UserRepository automaticamente já tem métodos como os seguintes, <br>
save(User user) → salvar ou atualizar um usuário <br>

findById(Long id) → buscar usuário pelo ID <br>

findAll() → buscar todos os usuários <br>

deleteById(Long id) → excluir usuário pelo ID <br>

```
package com.jobscheduler.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jobscheduler.project.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
}
```




### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.4/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.4/reference/web/servlet.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)