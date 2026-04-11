package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.cg.entity.User;
import com.cg.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	UserServiceImpl service;

	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		return service.getUser(id);
	}

	@PostMapping("/addUser")
	public User addUser(@RequestBody User u) {
		return service.addUser(u);
	}
}