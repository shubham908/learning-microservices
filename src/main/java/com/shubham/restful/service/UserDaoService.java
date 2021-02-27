package com.shubham.restful.service;

import java.util.*;

import com.shubham.restful.exception.UserNotFoundException;
import org.springframework.stereotype.Component;

import com.shubham.restful.model.User;

@Component
public class UserDaoService {

	private static List<User> userList = new ArrayList<>();
	private static int userCount = 3;

	static {
		userList.add(new User(1, "Thomas Tuchel", new Date()));
		userList.add(new User(2, "Jose Mourinho", new Date()));
		userList.add(new User(3, "Mauricio Pochettino", new Date()));
	}

	public List<User> getUsers() {
		return userList;
	}

	public User addUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		userList.add(user);

		return user;
	}

	public User getUser(Integer id) {
		User user = null;

		for (User u : userList) {
			if (u.getId().equals(id)) {
				user = u;
			}
		}

		return user;
	}

	public User deleteUser(Integer id) {
		User user = userList.stream()
            .filter(u -> u.getId().equals(id))
            .findFirst().orElseThrow(() -> new UserNotFoundException("User not found"));
		userList.remove(user);

		return user;
	}
}
