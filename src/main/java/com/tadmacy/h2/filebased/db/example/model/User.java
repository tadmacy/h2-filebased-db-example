package com.tadmacy.h2.filebased.db.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Entity
@Table(name = "users")
public class User {
	
    @Id
    
    @Column(name="ID", unique=true, updatable=false, nullable=false)
	@GeneratedValue
	private long id;

    @Column(name="username", unique=true, updatable=false, nullable=false)
    private String username;
    
    @Column(name="firstName", unique=false, updatable=true, nullable=false)
    private String firstName;
    
    @Column(name="lastName", unique=false, updatable=true, nullable=false)
    private String lastName;
    
    @Column(name="Email", unique=true, updatable=true, nullable=false)
    private String email;
    
    // Getters and setters

    public User() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
