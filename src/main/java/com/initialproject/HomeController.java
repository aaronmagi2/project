/*
 * Copyright 2016 Magi Consulting Inc.
 * 
 */
package com.initialproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Aaron Magi
 */
// tag::code[]
@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public String index() {
		return "index";
	}

}
// end::code[]