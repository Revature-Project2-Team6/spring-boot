package com.revature.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.dto.Credentials;
import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import com.revature.service.UserService;
import com.revature.util.JwtTokenManager;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
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
        try {
            User user = this.userService.authenticate(creds);
            String token = this.tokenManager.issueToken(user);

            response.addHeader("auth-token", token);
            response.addHeader("user-id", Integer.toString(user.getId()));
            response.addHeader("Access-Control-Expose-Headers", "auth-token, user-id");
            response.setStatus(200);
        } catch (AuthenticationException e) {
            response.setStatus(401);
        }
        return null;
    }

}
