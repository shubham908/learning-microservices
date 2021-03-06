package com.shubham.restful.controller;

import com.shubham.restful.exception.UserNotFoundException;
import com.shubham.restful.model.User;
import com.shubham.restful.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

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
  public EntityModel<User> getUser(@PathVariable Integer id) {
    User user = userDaoService.getUser(id);

    if (user == null) {
      throw new UserNotFoundException("user with id [" + id + "] was not found");
    }
    EntityModel<User> entityModel = EntityModel.of(user);
    WebMvcLinkBuilder link =
        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUserList());
    entityModel.add(link.withRel("all-users"));

    return entityModel;
  }

  @RequestMapping(value = "/user", method = RequestMethod.POST)
  public ResponseEntity<Object> adduser(@Valid @RequestBody User user) {
    User createdUser = userDaoService.addUser(user);

    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(createdUser.getId())
            .toUri();

    return ResponseEntity.created(location).build();
  }

  @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<User> deleteUser(@PathVariable Integer id) {
    User user = userDaoService.deleteUser(id);

    if (user == null) {
      throw new UserNotFoundException("User with id [" + id + "] was not found");
    } else {
      return new ResponseEntity<>(user, HttpStatus.NO_CONTENT);
    }
  }
}
