/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author amagi
 */
// tag::code[]
public interface TestSuiteRepository extends PagingAndSortingRepository<TestSuite, Long> {

	 @Query("SELECT ts FROM TestSuite ts JOIN FETCH ts.testCases WHERE ts.id = (:id)")
	    public TestSuite findByIdAndFetchTestCasesEagerly(@Param("id") Long id);
}
// end::code[]
