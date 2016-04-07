/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.*;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Component
public class DatabaseLoader implements CommandLineRunner {


	private final TestSuiteRepository repositoryTS;
	private final TestCaseRepository repositoryTC;
	private final UserRepository repositoryE;
	private final DeviceRepository repositoryD;

	@Autowired
	public DatabaseLoader(TestSuiteRepository repositoryTS, TestCaseRepository repositoryTC, UserRepository repositoryE, DeviceRepository repositoryD) {
		this.repositoryTS = repositoryTS;
		this.repositoryTC = repositoryTC;
		this.repositoryE = repositoryE;
		this.repositoryD = repositoryD;
	}

	@Override
	public void run(String... strings) throws Exception {

		// create users
		this.repositoryE.save(new User("Aaron", "Magi", "amagi", "Server guy"));
		this.repositoryE.save(new User("Al", "Herrera", "aherrera", "Android master"));
		this.repositoryE.save(new User("Stephen", "Moody", "smoody", "Wizard"));
		this.repositoryE.save(new User("Matt", "Gambardella", "mgambardella", "PM"));
		
		// create some devices
		
		// create samsung device (AL)
		this.repositoryD.save(new Device("samsung",
				"samsung-sm-n920a",
				"0915f940d8211d04",
				"samsung",
				"noblelteuc",
				"353876072319342",
				"14259749643",
				"17",
				"at&t",
				"us",
				"",
				"89014103278682386837",
				"310410868238683",
				"lte",
				"gsm",
				"5",
				"[37135,107899658,65]",
				"4 out of 5"));
		
		// create FAKE nexus
		this.repositoryD.save(new Device("google",
				"nexus-n920a",
				"2912f940d8211d04",
				"samsung",
				"noblelteuc",
				"353876072319342",
				"14259749643",
				"17",
				"at&t",
				"us",
				"",
				"89014103278682386837",
				"310410868238683",
				"lte",
				"gsm",
				"5",
				"[37135,107899658,65]",
				"4 out of 5"));
		
		
		TestCase tcA = new TestCase("amagi", 1, "test case a");
		TestCase tcB = new TestCase("amagi", 1, "test case b");
		TestCase tcC = new TestCase("amagi", 1, "test case c");
		TestCase tcD = new TestCase("amagi", 1, "test case d");
		this.repositoryTC.save(tcA);
		this.repositoryTC.save(tcB);
		this.repositoryTC.save(tcC);
		this.repositoryTC.save(tcD);

		TestSuite ts = new TestSuite(new LinkedList<TestCase>(){{add(tcA);add(tcB);add(tcC);}},
				"amagi", 
				"my test suite");
		

		for(TestCase tc: ts.getTestCases()) {
			System.out.println("new order is " + tc.toString());
		}
		Long id = this.repositoryTS.save(ts).getId();
		
		TestSuite ts2 = new TestSuite(new LinkedList<TestCase>(){{add(tcA);}},
				"amagi", 
				"my 2nd test suite");
		
		this.repositoryTS.save(ts2);
		
		System.out.println("Test lazy loading");
		TestSuite suite1 = this.repositoryTS.findByIdAndFetchTestCasesEagerly(id);
		
		System.out.println("Have object");
		
		System.out.println("Testing id " + suite1.getId());
		System.out.println("Testing size " + suite1.getTestCases().size());
		
		LinkedList<TestCase> newList = new LinkedList<TestCase>();
		
		System.out.println("Getting old list");
		List<TestCase> oldList = suite1.getTestCases();

		System.out.println("Creating new list");
		TestCase tc1 = oldList.get(2);
		System.out.println("checking");
		System.out.println("tc1 " + tc1.getDescription());
		
		newList.add(tc1);
		newList.add(oldList.get(0));
		newList.add(oldList.get(1));
		
		//System.out.println("Old list " + oldList.toString());
		
		//System.out.println("New list " + newList.toString());
		
		suite1.setTestCases(newList);
		//System.out.println("Suite list " + suite1.getTestCases().toString());
		
		
		for(TestCase tc: suite1.getTestCases()) {
			System.out.println("new order is " + tc.getId());
		}
		
		Thread.sleep(30000);
		
		this.repositoryTS.save(suite1);
		
		
		
	}
}
// end::code[]