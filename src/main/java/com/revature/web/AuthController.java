package com.revature.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JwtTokenManager;

@RestController
@CrossOrigin(origins="*", allowedHeaders="*")
@RequestMapping("/login")
public class AuthController {
	private UserService userService;
	private JwtTokenManager tokenManager;
	
	@Autowired
	public AuthController(UserService userService, JwtTokenManager tokenManager) {
		this.userService = userService;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping
	public User login(@RequestBody Credentials creds, HttpServletResponse response) {
		User user = userService.authenticate(creds);
		if(user != null) {
			String token = tokenManager.issueToken(user);
			
			response.addHeader("auth-token", token);
			response.addHeader("Access-Control-Expose-Headers", "auth-token");
			response.setStatus(200);
			
			return user;
		}else {
			response.setStatus(401);
			return null;
		}
	}
}
