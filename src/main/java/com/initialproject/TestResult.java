/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import javax.persistence.Entity;
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
@Table(name = "result")
public class TestResult {

	private @Id @GeneratedValue Long id;
	
	private Long testSuiteID;
	private Long testCaseID;
	private String deviceID;
	
	private String status;
	private String date;
	private String timeToRun;
	private String androidDeviceData;
	private String performanceMetrics;
	private String videoLocation;
	private String screenShotLocation;
	private String log;
	
	private @Version @JsonIgnore Long version;
	
	private TestResult() {}

	public TestResult(Long testSuiteID,
			Long testCaseID,
			String deviceID,
			String status, 
			String date, 
			String timeToRun, 
			String androidDeviceData,
			String performanceMetrics,
			String videoLocation,
			String screenShotLocation,
			String log) {
		this.testSuiteID = testSuiteID;
		this.testCaseID = testCaseID;
		this.deviceID = deviceID;
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