/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "test_result")
public class TestResult {

	private @Id @GeneratedValue Long id;
	

	@OneToOne(fetch = FetchType.LAZY)
	private TestExecution testExecution;

	@OneToOne(fetch = FetchType.LAZY)
	private TestSuite testSuite;

	@OneToOne(fetch = FetchType.LAZY)
	private TestCase testCase;

	@OneToOne(fetch = FetchType.LAZY)
	private Device device;
	
	private String status;
	private String date;
	private int timeToRun;
	private String androidDeviceData;
	private String performanceMetrics;
	private String videoLocation;
	private String screenShotLocation;
	private String log;
	
	private @Version @JsonIgnore Long version;
	
	private TestResult() {}

	public TestResult(TestExecution testExecution,
			TestSuite testSuite,
			TestCase testCase,
			Device device,
			String status, 
			String date, 
			int timeToRun, 
			String androidDeviceData,
			String performanceMetrics,
			String videoLocation,
			String screenShotLocation,
			String log) {
		this.testExecution = testExecution;
		this.testSuite = testSuite;
		this.testCase = testCase;
		this.device = device;
		this.status = status;
		this.date = date;
		this.timeToRun = timeToRun;
		this.androidDeviceData = androidDeviceData;
		this.performanceMetrics = performanceMetrics;
		this.videoLocation = videoLocation;
		this.screenShotLocation = screenShotLocation;
		this.log = log;
	}	
}
// end::code[]