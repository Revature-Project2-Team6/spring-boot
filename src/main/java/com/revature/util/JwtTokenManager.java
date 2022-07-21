package com.revature.util;

// this class is responsible for managing JWT tokens
// there will be errors in this until the auth exception
// and user models are created
import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component // we decalre is as a Spring Bean (object managed by the Spring IoC container)
public class JwtTokenManager {

    private final Key key; // from java.security
    private final Logger logger = LoggerFactory.getLogger(JwtTokenManager.class);

    public JwtTokenManager() {
        // what is a key?
        // a set of public keys used to verify a token and have it be parsed by our
        // server
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    // this builds the payload which is encrypted info about the user we're
    // authenticating
    public String issueToken(User user) {
        return Jwts.builder() // io.jsonwebtoken
            // payload
            .setId(String.valueOf(user.getId()))
            .setSubject(user.getUsername())
            .setIssuer("Rolodex API") // the source that generated the token
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .signWith(this.key)
            .compact();
    }

    public int parseUserIdFromToken(String token) {

        try {
            return Integer.parseInt(Jwts.parserBuilder()
                .setSigningKey(this.key)
                .build()
                // this is the way in which we can READ user data from a token
                .parseClaimsJws(token)
                .getBody()
                .getId());

        } catch (Exception e) {
            this.logger.warn("JWT error parsing user id from token");
            throw new AuthenticationException("Unable to parse user id from JWT. Please sign in again");
        }
    }

}
