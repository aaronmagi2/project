/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Aaron Magi
 */
// tag::code[]
public interface DeviceRepository extends PagingAndSortingRepository<Device, Long> {

}
// end::code[]
