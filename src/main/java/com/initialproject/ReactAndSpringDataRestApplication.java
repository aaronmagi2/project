/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Aaron Magi
 */
// tag::code[]
@SpringBootApplication
public class ReactAndSpringDataRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactAndSpringDataRestApplication.class, args);
	}
}
// end::code[]