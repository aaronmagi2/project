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
public interface TestExecutionRepository extends PagingAndSortingRepository<TestExecution, Long> {
/*
	 @Query("SELECT te FROM TestExecution te JOIN FETCH te.testCases, te.devices, te.testSuites WHERE te.id = (:id)")
	    public TestExecution findByIdAndFetchDependenciesEagerly(@Param("id") Long id);
	*/    
}
// end::code[]
