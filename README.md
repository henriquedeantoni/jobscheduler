## JOB SCHEDULER


### USER RESOURCE

* Controller:  a classe UserResource é uma controller REST, ela é encarregada de expor os endpoints da API. A anotation @Autowired é encarregada de efetuar a injeção de dependência, injeta a instância da camada de service para dentro da camada controller. Note que o @GetMapping são os mapeamentos dos métodos do tipo GET das requisições, a primeira é um findAll() e a segunda um findById(). 

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

* User Entity: 

```

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@OneToMany(mappedBy = "client")
	private List<OrderService> orders =  new ArrayList<>();; 
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

```
## Testes no H2 CONSOLE


### Queries

```
SELECT * 
FROM TB_JOB j
INNER JOIN TB_JOB_CATEGORY jc ON j.ID = jc.JOB_ID
INNER JOIN TB_CATEGORY c ON jc.CATEGORY_ID = c.ID
WHERE UPPER(c.NAME) IN ('TRAINING');
```

## Endpoints

### Category

* [GET]

url

```
http://localhost:8080/categories
```

Response:

```
[
  {
    "id": 1,
    "name": "Maintenance"
  },
  {
    "id": 2,
    "name": "Training"
  },
  {
    "id": 3,
    "name": "Programming"
  },
  {
    "id": 4,
    "name": "Support"
  },
  {
    "id": 5,
    "name": "Projects"
  }
]
```

url

```
http://localhost:8080/categories/{id}
```
example:

```
http://localhost:8080/categories/1
```

Response:

```
{
  "id": 1,
  "name": "Maintenance"
}
```


### Client

[GET]

url

```
http://localhost:8080/clients
```

Response:

```
[
    {
        "id": 1,
        "name": "Dell Inc.",
        "ssnNumber": "932849324293",
        "tinNumber": ""
    },
    {
        "id": 2,
        "name": "Airplay Tech",
        "ssnNumber": "87324283943",
        "tinNumber": ""
    },
    {
        "id": 3,
        "name": "John Doe",
        "ssnNumber": "",
        "tinNumber": "39328479234"
    },
    {
        "id": 4,
        "name": "Phill Collins",
        "ssnNumber": "",
        "tinNumber": "13124332552"
    },
    {
        "id": 5,
        "name": "Frank Zappa",
        "ssnNumber": "",
        "tinNumber": "461523465"
    }
]
```

url

```
http://localhost:8080/clients/{id}
```

Response:

```
{
    "id": 4,
    "name": "Phill Collins",
    "ssnNumber": "",
    "tinNumber": "13124332552"
}
```

url

```
http://localhost:8080/clients/2
```

Response:

```
{
    "id": 2,
    "name": "Airplay Tech",
    "ssnNumber": "87324283943",
    "tinNumber": ""
}
```

url

```
http://localhost:8080/clients/name/{nameSegment}
```

Example:

```
http://localhost:8080/clients/name/dell
```

Response:


```
[
    {
        "id": 1,
        "name": "Dell Inc.",
        "ssnNumber": "932849324293",
        "tinNumber": ""
    }
]
```

url

```
http://localhost:8080/clients/ssn/{ssnSegment}
```

Example:

```
http://localhost:8080/clients/ssn/849324
```

Response:


```
[
  {
    "id": 1,
    "name": "Dell Inc.",
    "ssnNumber": "932849324293",
    "tinNumber": ""
  }
]
```


url

```
http://localhost:8080/clients/tin/{tinSegment}
```

Example:

```
http://localhost:8080/clients/tin/93
```

Response:


```
[
  {
    "id": 1,
    "name": "Dell Inc.",
    "ssnNumber": "932849324293",
    "tinNumber": ""
  }
]
```




[POST]


url

```
http://localhost:8080/clients
```

Request Body:

```
    {
        "name": "Pizza Planet",
        "ssnNumber": "932849324293",
        "tinNumber": ""
    }
```

### User

[GET]

url

```
http://localhost:8080/users
```

Response:

<details>
<summary>Exemplo</summary>

```
[
  {
    "id": 1,
    "name": "Clara Almeida",
    "email": "claraalma@gmail.com",
    "phone": "55998745321",
    "passwordHash": "h54fiw21u",
    "userStatus": "ACTIVE"
  },
  {
    "id": 2,
    "name": "Ricardo Peixoto",
    "email": "rikapeix@gmail.com",
    "phone": "11997524536",
    "passwordHash": "123ABCDs456",
    "userStatus": "ACTIVE"
  },
  {
    "id": 3,
    "name": "Ana Silva",
    "email": "anasilvafr@gmail.com",
    "phone": "16987451265",
    "passwordHash": "123qiud",
    "userStatus": "ONAPPROVAL"
  },
  {
    "id": 4,
    "name": "Carlos Santos",
    "email": "carlosantosrp@gmail.com",
    "phone": "21997850123",
    "passwordHash": "123qiud",
    "userStatus": "ONAPPROVAL"
  }
]
```
</details>

url

```
http://localhost:8080/users/{id}
```

Example:

```
http://localhost:8080/users/1
```

Response:

<details>
<summary>Exemplo</summary>

```
{
  "id": 1,
  "name": "Clara Almeida",
  "email": "claraalma@gmail.com",
  "phone": "55998745321",
  "passwordHash": "h54fiw21u",
  "userStatus": "ACTIVE"
}
```
</details>

url

```
http://localhost:8080/users/email/{email}
```

Example:

```
http://localhost:8080/users/email/claraalma@gmail.com
```

Response:

<details>
<summary>Exemplo</summary>

```
{
  "id": 1,
  "name": "Clara Almeida",
  "email": "claraalma@gmail.com",
  "phone": "55998745321",
  "passwordHash": "h54fiw21u",
  "userStatus": "ACTIVE"
}
```
</details>

### Job

Url

```
GET /jobs?categories=Training,Programming
```

Response:

```
[
  {
    "id": 2,
    "title": "Software Support",
    "description": "Nulla eu imperdit purus. Maecenas an.",
    "duration": 16,
    "price": 800,
    "hourPrice": 50,
    "location": "",
    "categories": [
      {
        "id": 3,
        "name": "Programming"
      },
      {
        "id": 4,
        "name": "Support"
      }
    ]
  },
  {
    "id": 3,
    "title": "Design Training",
    "description": "Nam eleifend maximus tortor, at mols.",
    "duration": 8,
    "price": 600,
    "hourPrice": 75,
    "location": "",
    "categories": [
      {
        "id": 2,
        "name": "Training"
      }
    ]
  }
 ]

```
Url

```
http://localhost:8080/jobs?categories=Training,Programming&match=all
```
Response

```

[
  {
    "id": 4,
    "title": "CyberSecurity Training",
    "description": "Donec aliquet odio ac roncus cursus.",
    "duration": 24,
    "price": 2160,
    "hourPrice": 90,
    "location": "",
    "categories": [
      {
        "id": 2,
        "name": "Training"
      },
      {
        "id": 3,
        "name": "Programming"
      }
    ]
  }
]

```

* [POST]

Url

```
http://localhost:8080/categories
```
Body

```
{
 "name": "Sales"
} 
```

Response

```
{
    "id": 6,
    "name": "Sales"
}
```


### Job Order

Url

```
http://localhost:8080/job-orders/vendor?name=Ana%20Silva
```

Response:

```
[
  {
    "id": 4,
    "moment": "2020-01-11T21:21:22Z",
    "orderStatus": "DELIVERED",
    "items": [],
    "payment": null,
    "total": 0
  }
]
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
