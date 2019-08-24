package com.rest.restfulwebservices.user;



	import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
	import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.hateoas.Resource;
	import org.springframework.hateoas.mvc.ControllerLinkBuilder;
	import org.springframework.http.ResponseEntity;
	import org.springframework.web.bind.annotation.DeleteMapping;
	import org.springframework.web.bind.annotation.GetMapping;
	import org.springframework.web.bind.annotation.PathVariable;
	import org.springframework.web.bind.annotation.PostMapping;
	import org.springframework.web.bind.annotation.RequestBody;
	import org.springframework.web.bind.annotation.RestController;
	import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

	@RestController
	public class UserJPAResource {


		@Autowired
		UserRepository userRepository;
		@Autowired
		PostRepository postRepository;
		@GetMapping(value = "/users")
		public List<User> retrieveAllUsers(){
//			return userDao.findAll();
			return userRepository.findAll();
		}
		
		//we are using hateoas for this method 
		//which returns user data + link for the consumer 
		//and return the resouces(data+link)
		@GetMapping(path="/users/{id}")
		public Resource<User> retrieveUser(@PathVariable int id) {
//		User user=userDao.findOne(id);
			Optional<User> user=userRepository.findById(id);
		if(!user.isPresent())
			throw new UserNotFoundException("this id="+ id +" has no user");
		
		Resource<User> resource = new Resource<User>(user.get());
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		return resource;
		}
		@DeleteMapping(path="/users/{id}")
		public void deleteUser(@PathVariable int id) {
		userRepository.deleteById(id); 
		}
		
		@PostMapping(path="/users")
		public  ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
			User savedUser=userRepository.save(user);
			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(savedUser.getId())
					.toUri();
			return (ResponseEntity<Object>) ResponseEntity.created(location).build();
		}
		
		@GetMapping("/users/{id}/posts")
		public List<Post> retrieveAllUserPost(@PathVariable Integer id){
			Optional<User> user=userRepository.findById(id);
			if(!user.isPresent()) {
				throw new UserNotFoundException("id"+ id +"not found");
			}
			return user.get().getPosts();
		}
		
		//do validation as your exercise
		@PostMapping("/users/{id}/posts")
		public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
			Optional<User> userOptional = userRepository.findById(id);
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("id-"+id+" not found");
			}
			User user = userOptional.get();
			post.setUser(user);
			postRepository.save(post);
			
			URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
	}
