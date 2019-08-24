//package com.rest.restfulwebservices.user;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
//import java.net.URI;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.hateoas.Resource;
//import org.springframework.hateoas.mvc.ControllerLinkBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//@RestController
//public class UserResource {
//
//	//retrieveAllUsers
//	@Autowired
//	UserDaoService userDao;
//	@GetMapping(value = "/users")
//	public List<User> retrieveAllUsers(){
//		return userDao.findAll();
//	}
//	
//	//we are using hateoas for this method 
//	//which returns user data + link for the consumer 
//	//and return the resouces(data+link)
//	@GetMapping(path="/users/{id}")
//	public Resource<User> retrieveUser(@PathVariable int id) {
//	User user=userDao.findOne(id);
//	if(user==null)
//		throw new UserNotFoundException("this id="+ id +" has no user");
//	
//	Resource<User> resource = new Resource<User>(user);
//	ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
//	resource.add(linkTo.withRel("all-users"));
//	return resource;
//	}
//	@DeleteMapping(path="/users/{id}")
//	public void deleteUser(@PathVariable int id) {
//	User user=userDao.deleteUserById(id); 
//	if(user==null) {
//		throw new UserNotFoundException("this id="+ id +" has no user");
//	}
//	}
//	
//	@PostMapping(path="/users")
//	public  ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
//		User savedUser=userDao.save(user);
//		URI location = ServletUriComponentsBuilder
//				.fromCurrentRequest()
//				.path("/{id}")
//				.buildAndExpand(savedUser.getId())
//				.toUri();
//		return (ResponseEntity<Object>) ResponseEntity.created(location).build();
//	}
//	
//	//retrieveUser(@PathVariable int id)
//	
//}
