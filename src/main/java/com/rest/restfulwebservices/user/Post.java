package com.rest.restfulwebservices.user;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description;
	
	@ManyToOne (fetch=FetchType.LAZY)//many posts by one user 
	@JsonIgnore
	private User user;
	
	public Post() {}

	public Post(Integer id, String description, User user) {
		super();
		this.id = id;
		this.description = description;
		this.user = user;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	//do not create user in toString method otherwise circular reference occurs 
	@Override
	public String toString() {
		return "Post [id=" + id + ", description=" + description + "]";
	}

	
	

}
