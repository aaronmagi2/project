/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Data
@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String firstName;
	private String lastName;
	private String userName;
	private String description;

	private @Version @JsonIgnore Long version;

	private User() {}

	public User(String firstName, String lastName, String userName, String description) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.description = description;
	}
}
// end::code[]