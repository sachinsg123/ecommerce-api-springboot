package com.ecommerceapi.services;

import com.ecommerceapi.Exception.UserException;
import com.ecommerceapi.model.User;

public interface UserService{
	
	public User getUserById(int id) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;
	

}
