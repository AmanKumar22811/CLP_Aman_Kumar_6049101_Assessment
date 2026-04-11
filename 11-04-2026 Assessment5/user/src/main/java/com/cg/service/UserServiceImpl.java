package com.cg.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entity.User;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository repo;

	@Override
	public User getUser(Long id) {
		return repo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
	}

	@Override
	public User addUser(User u) {
		return repo.save(u);
	}

}
