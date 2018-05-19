package com.investedu.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping("/")
	public String stockApi() {
		return "{ \"stock\" : \"up\"  }";
	}
}
