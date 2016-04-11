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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Data
@Entity
@Table(name = "test_execution")
public class TestExecution {

	private @Id @GeneratedValue Long id;

	//@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TestSuite> testSuites;

	//@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TestCase> testCases;

	//@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<Device> devices;

	//@Fetch(FetchMode.JOIN)
	@ManyToMany(fetch = FetchType.LAZY)
	private Set<TestResult> testResults;
	
	private String owner;
	private String description;

	private @Version @JsonIgnore Long version;

	private TestExecution() {}

	public TestExecution(List<TestSuite> testSuites, List<TestCase> testCases, Set<Device> devices, Set<TestResult> testResults, String owner, String description) {
		this.testSuites = testSuites;
		this.testCases = testCases;
		this.devices = devices;
		this.testResults = testResults;
		this.owner = owner;
		this.description = description;
	}
}
// end::code[]