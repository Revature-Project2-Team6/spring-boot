package com.revature.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import com.revature.util.RegexUtil;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data @AllArgsConstructor @NoArgsConstructor
public class User {
	
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotBlank
	@Length(min=3)
	@Column(nullable=false, unique=true)
	private String username;
	
	@NotBlank
	@Pattern(regexp=RegexUtil.PASSWORD_REGEX)
	@Column(nullable=false)
	private String password;
	
	@NotBlank
	@Email
	@Column(nullable=false, unique=true)
	private String email;
	

	public User(@NotBlank @Length(min = 5) @Pattern(regexp = "\"[a-zA-Z][a-zA-Z0-9]*") String username,
			@NotBlank @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$") String password,
			@NotBlank @Email String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
