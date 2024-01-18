package com.ecommerceapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerceapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmail(String email);

}
