package com.investedu.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class APIController {

	@RequestMapping("/")
	public String stockApi() {
		return "{ \"stock\" : \"up\"  }";
	}
	
	/*@RequestMapping(
			value = "/v1.0/register",
			method = RequestMethod.POST )
	@ResponseBody
	public Message registerUser(@RequestBody User input) {
		System.out.println(input.getName());
		Message msg = new Message();
		msg.setMessage("success");
		return msg;
	}*/
}
