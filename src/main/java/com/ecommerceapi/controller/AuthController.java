package com.ecommerceapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerceapi.Exception.UserException;
import com.ecommerceapi.Repository.UserRepository;
import com.ecommerceapi.config.JwtProvider;
import com.ecommerceapi.model.User;
import com.ecommerceapi.services.CustomeUserServiceImplementation;

@RestController
@RequestMapping("/api")
public class AuthController{
	@Autowired
	private UserRepository userRepo;
	
	private JwtProvider jwtProvider;
	

	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomeUserServiceImplementation customuserservice;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse>createUser(@RequestBody User user) throws UserException{
		
		String email = user.getEmail();
		String firstname = user.getFirstName();
		String lastname = user.getLastName();
		String mobile = user.getMobile();
		String password = user.getPassword();
		String role = user.getRole();
		
		User isUserExist = userRepo.findByEmail(email);
		
		if(isUserExist != null)
		{
		   throw new UserException("user found on this email address");
		}
		
		User createdUser = new User();
		
		createdUser.setFirstName(firstname);
		createdUser.setLastName(lastname);
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setMobile(mobile);
		createdUser.setRole(role);
		
		User savedUser = userRepo.save(createdUser);
		
	Authentication authentication =	new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
	SecurityContextHolder.getContext().setAuthentication(authentication);
	
	String token = jwtProvider.generateToken(authentication);
	
	AuthResponse authresponse = new AuthResponse(token, "signup successfully");
	
	return new ResponseEntity<AuthResponse>(authresponse,HttpStatus.CREATED);
	
	
		
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse>loginUser(@RequestBody LoginRequest loginrequest){
		
		
		String username = loginrequest.getEmail();
		String password = loginrequest.getPassword();
		
		Authentication authentication = authenticate(username, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authresponse = new AuthResponse(token, "login successfully");
		
		return new ResponseEntity<AuthResponse>(authresponse,HttpStatus.CREATED);
		
		
	}

	
     // authenticate method implementation for login authentication 
	private Authentication authenticate(String username, String password){
		
		UserDetails userdetails = customuserservice.loadUserByUsername(username);
		
		if(userdetails == null){
			throw new BadCredentialsException("invalid username");
		}
		
		if(!passwordEncoder.matches(password, userdetails.getPassword())) {
			throw new BadCredentialsException("invalid password");
			
		}
		
		return new UsernamePasswordAuthenticationToken( userdetails,null, userdetails.getAuthorities());
	}

}
