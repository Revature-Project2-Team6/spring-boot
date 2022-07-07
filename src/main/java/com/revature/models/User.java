package com.revature.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
	@Length(min=5)
	@Pattern(regexp="\"[a-zA-Z][a-zA-Z0-9]*")
	@Column(nullable=false, unique=true)
	private String username;
	
	@NotBlank
	@Pattern(regexp=RegexUtil.PASSWORD_REGEX)
	@Column(nullable=false)
	private String password;
	
	@NotBlank
	@Pattern(regexp=RegexUtil.EMAIL_REGEX)
	@Column(nullable=false, unique=true)
	private String email;

//	@OneToMany
//	private List<Character> characters;
	
	public User(@NotBlank @Length(min = 5) @Pattern(regexp = "\"[a-zA-Z][a-zA-Z0-9]*") String username,
			@NotBlank @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\\\d)[A-Za-z\\\\d]{8,}$") String password,
			@NotBlank @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])") String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	

}
