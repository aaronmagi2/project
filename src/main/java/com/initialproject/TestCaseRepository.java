/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author amagi
 */
// tag::code[]
public interface TestCaseRepository extends PagingAndSortingRepository<TestCase, Long> {

}
// end::code[]
