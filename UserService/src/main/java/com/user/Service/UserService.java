package com.user.Service;

import java.util.List;

import com.user.Entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAlluser();
	
	User getUser(String userId);
	
	User updateUser(User user,String userId);
	
	void deleteUser(String userId);
	
	
	
	
}
