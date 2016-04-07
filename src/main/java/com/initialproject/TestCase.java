/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author amagi
 */
// tag::code[]
@Data
@Entity
@Table(name = "test_case")
public class TestCase {

	private @Id @GeneratedValue Long id;
	private String owner;
	private int versionNumber;
	private String description;
	@ManyToMany(mappedBy="testCases")
	private List<TestSuite> testSuites;  // mapped in TestSuite

	private @Version @JsonIgnore Long version;

	private TestCase() {}

	public TestCase(String owner, int versionNumber, String description) {
		this.owner = owner;
		this.versionNumber = versionNumber;
		this.description = description;
	}

	public TestCase(String owner, int versionNumber, String description, List<TestSuite> testSuites) {
		this.owner = owner;
		this.versionNumber = versionNumber;
		this.description = description;
		this.testSuites = testSuites;
	}
}
// end::code[]