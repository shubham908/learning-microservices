package com.shubham.restful.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shubham.restful.model.User;
import com.shubham.restful.service.UserDaoService;

@RestController
public class UserController {

	private UserDaoService userDaoService;

	@Autowired
	public UserController(UserDaoService userDaoService) {
		this.userDaoService = userDaoService;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> getUserList() {
		return userDaoService.getUsers();
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable Integer id) {
		return userDaoService.getUser(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity<Object> adduser(@RequestBody User user) {
		User createdUser = userDaoService.addUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(createdUser.getId()).toUri();

		return ResponseEntity.created(location).build();
	}
}
