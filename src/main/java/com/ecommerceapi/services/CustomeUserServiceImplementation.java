package com.ecommerceapi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerceapi.Repository.UserRepository;
import com.ecommerceapi.model.User;

@Service
public class CustomeUserServiceImplementation implements UserDetailsService{
	@Autowired
	private UserRepository userRepo;
	
	private CustomeUserServiceImplementation(UserRepository userRepo) {
		
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	     
		User user = userRepo.findByEmail(username);
		
		if(user == null) {
			
			throw new UsernameNotFoundException("user not found with  "+username);
		}
		  
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
	}

}
