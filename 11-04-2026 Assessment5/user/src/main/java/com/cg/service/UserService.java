package com.cg.service;

import com.cg.entity.User;

public interface UserService {

	User getUser(Long id);
	
	User addUser(User u);

}
