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

	private final TestExecutionRepository repositoryTE;
	private final TestSuiteRepository repositoryTS;
	private final TestCaseRepository repositoryTC;
	private final UserRepository repositoryE;
	private final DeviceRepository repositoryD;
	private final TestResultRepository repositoryTR;

	@Autowired
	public DatabaseLoader(TestExecutionRepository repositoryTE,
			TestSuiteRepository repositoryTS, 
			TestCaseRepository repositoryTC, 
			UserRepository repositoryE, 
			DeviceRepository repositoryD, 
			TestResultRepository repositoryTR) {
		this.repositoryTE = repositoryTE;
		this.repositoryTS = repositoryTS;
		this.repositoryTC = repositoryTC;
		this.repositoryE = repositoryE;
		this.repositoryD = repositoryD;
		this.repositoryTR = repositoryTR;
	}

	@Override
	public void run(String... strings) throws Exception {

		// create users
		this.repositoryE.save(new User("Aaron", "Magi", "amagi", "Developer"));
		this.repositoryE.save(new User("Al", "Herrera", "aherrera", "Android master"));
		this.repositoryE.save(new User("Stephen", "Moody", "smoody", "Wizard"));
		this.repositoryE.save(new User("Matt", "Gambardella", "mgambardella", "PM"));
	
		// create some devices
		
		// create samsung device (AL)
		Device samsung = new Device("samsung",
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
				"4 out of 5");
		this.repositoryD.save(samsung);
		
		// create FAKE nexus
		Device nexus = new Device("google",
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
				"4 out of 5");
		this.repositoryD.save(nexus);
		
		
		TestCase tcA = new TestCase("amagi", 1, "test case A");
		TestCase tcB = new TestCase("amagi", 1, "test case B");
		TestCase tcC = new TestCase("amagi", 1, "test case C");
		TestCase tcD = new TestCase("amagi", 1, "test case D");
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
		
		TestSuite ts3 = new TestSuite(new LinkedList<TestCase>(){{add(tcA);add(tcD);}},
				"amagi", 
				"my 3nd test suite");
		
		this.repositoryTS.save(ts3);
		
		
		// create Test Execution
		TestExecution te = new TestExecution(new LinkedList<TestSuite>(){{add(ts);}},
				new LinkedList<TestCase>(){{add(tcD);add(tcA);}},
				new HashSet<Device>(){{add(samsung);add(nexus);}},
				new HashSet<TestResult>(),
				"amagi", 
				"my test execution ONE");
		
		Long teID = this.repositoryTE.save(te).getId();
		
		
		
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
		
		// TEST EXECUTION TEST
		// Run pretending to create a execution and run through results for each test case
		// create Test Execution
		TestExecution te2 = new TestExecution(new LinkedList<TestSuite>(){{add(ts2);add(ts3);}},
				new LinkedList<TestCase>(){{add(tcD);add(tcA);}},
				new HashSet<Device>(){{add(samsung);add(nexus);}},
				new HashSet<TestResult>(),
				"amagi", 
				"test execution TWO");
		
		this.repositoryTE.save(te2);
				
	
		HashSet<TestResult> resultSet = new HashSet<TestResult>();
		
		// HOOK INTO ADB 
		
		// FOR EACH DEIVCE Create a container to run tests
		for (Device device : te2.getDevices()) {
			System.out.println("Testing Device Manufacture: " + device.getManufacturer() + " Model: " + device.getModel() + " softwareVersion: " + device.getSoftwareVersion());
			
			// Call to device....
			// run a pretend ADB calls for the TE 
			
			// TODO the following is only for test purposes, this would be written into a separate Test Execution Handler class
			
			// TEST THE TEST SUITES
			for(TestSuite testSuite: te2.getTestSuites()) {
				System.out.println("TESTING SUITE " + testSuite.getDescription());
				
				// for each test case preform the test
				for(TestCase testCase: testSuite.getTestCases()) {
					System.out.println("STARTING TEST FOR : " + testCase.getDescription());
					
					// hook into ADB and do test
					System.out.println("TEST FINISHED FOR : " + testCase.getDescription());
					
					
					Random ran = new Random();
					
					// Create RESULT object
					TestResult testResult = new TestResult(te2,
					testSuite,
					testCase,
					device,
					new String(ran.nextInt(1) == 0 ? "PASSED" : "FAILED"), 
					new Date().toString(), 
					ran.nextInt(10000),
					"androidDeviceData",
					"performanceMetrics",
					"videoLocation",
					"screenShotLocation",
					"log");
					

					// save the result
					resultSet.add(repositoryTR.save(testResult));
					
					// Add the test result
					// TODO this would be done as a whole list once the test finishes 
					//te2.getTestResults().add(testResult);
				}
				
			}
			
			// Now test the individual test cases
			// for each test case perform the test
			for(TestCase testCase: te2.getTestCases()) {
				System.out.println("STARTING TEST FOR : " + testCase.getDescription());
				
				// hook into ADB and do test
				System.out.println("TEST FINISHED FOR : " + testCase.getDescription());
				
				
				Random ran = new Random();
				
				// Create RESULT object
				TestResult testResult = new TestResult(te2,
				null,    // there is no test suite associated with these tests
				testCase,
				device,
				new String(ran.nextInt(1) == 0 ? "PASSED" : "FAILED"), 
				new Date().toString(), 
				ran.nextInt(10000),
				"androidDeviceData",
				"performanceMetrics",
				"videoLocation",
				"screenShotLocation",
				"log");
				
				
				// save the result
				resultSet.add(repositoryTR.save(testResult));
				
				// Add the test result
				// TODO this would be done as a whole list once the test finishes 
				//te2.getTestResults().add(testResult);
			}
		}
		
		/*
		System.out.println("going to save te2");
		repositoryTE.save(te2);

		System.out.println("going to add");
		te2.getTestResults().addAll(resultSet);
		System.out.println("going to save te2");
		repositoryTE.save(te2);
		
		*/
		
		
		/*
		for(TestCase tc: suite1.getTestCases()) {
			System.out.println("new order is " + tc.getId());
		}
		
		//Thread.sleep(30000);
		
		this.repositoryTS.save(suite1);
		
		
		// Run a TE
		
		
		// test  TE
		
		System.out.println("Getting TE with id " + teID);
		TestExecution teFromDB = this.repositoryTE.findOne(teID);
		System.out.println("Have TE");
		System.out.println("TE = " + teFromDB.getDescription());
		for(Device d : teFromDB.getDevices()) {
			System.out.println("has device");
			System.out.println("Device " + d.getSerial());
		}
		*/
		/*
		System.out.println("Getting TE with id " + teID);
		TestExecution teFromDB = this.repositoryTE.findByIdAndFetchDependenciesEagerly(teID);
		System.out.println("Have TE");
		for(Device d : teFromDB.getDevices()) {
			
			System.out.println("Device " + d.getSerial());
		}
		*/
		
	}
	
	
	
	
}
// end::code[]