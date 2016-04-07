/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Data
@Entity
@Table(name = "test_suite")
public class TestSuite {

	private @Id @GeneratedValue Long id;
	
	@ManyToMany
	private List<TestCase> testCases;
	private String owner;
	private String description;

	private @Version @JsonIgnore Long version;

	private TestSuite() {}

	public TestSuite(List<TestCase> testCases, String owner, String description) {
		this.testCases = testCases;
		this.owner = owner;
		this.description = description;
	}
}
// end::code[]