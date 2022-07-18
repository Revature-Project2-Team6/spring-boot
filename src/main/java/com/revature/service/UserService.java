package com.revature.service;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.data.UserRepository;
import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.models.User;

@Service
public class UserService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	public User authenticate(Credentials creds) {

		return userRepo.findUserByUsernameAndPassword(creds.getUsername(), creds.getPassword())
				.orElseThrow(AuthenticationException::new);
	}
	
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User add(User u) {
		
		return userRepo.save(u);
	}
	
	@Transactional(readOnly = true)
	public Set<User> findAll() {
		// here we are using the stream API to transform the List to a Set to avoid duplicates
		return userRepo.findAll().stream().collect(Collectors.toSet());
	}
	
	@Transactional(readOnly = true)
	public User getByUsername(String username) {

		return userRepo.findByUsername(username) // in the case that no User object can be returned, throw an exception
				.orElseThrow(UserNotFoundException::new);
	}

	@Transactional(readOnly = true)
	public User getById(int id) {

		if (id <= 0) {
			log.warn("Id cannot be <= 0. Id passed was: {}", id);
			return null;
		} else {
			return userRepo.findById(id).orElseThrow(UserNotFoundException::new);
		}

	}
	
	@Transactional(propagation = Propagation.REQUIRED) // default setting of transactions in Spring
	public void remove(int id) {
		userRepo.deleteById(id);
	}

    public User update(User u) {
        return userRepo.save(u);
    }

}
