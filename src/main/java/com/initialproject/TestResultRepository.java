package com.initialproject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author amagi
 */
public interface TestResultRepository extends PagingAndSortingRepository<TestResult, Long> {
	
/*
	 @Query("SELECT ts FROM TestSuite ts JOIN FETCH ts.testCases WHERE ts.id = (:id)")
	    public TestSuite findByTestSuiteIDAndFetchTestCasesEagerly(@Param("id") Long id);
	 
	 @Query("SELECT tc FROM TestCase tc JOIN WHERE tc.id = (:id)")
	    public TestCase findByTestCaseID(@Param("id") Long id);
	 
	 @Query("SELECT d FROM Device d JOIN WHERE d.id = (:id)")
	    public Device findByDeviceID(@Param("id") Long id);
*/

}
