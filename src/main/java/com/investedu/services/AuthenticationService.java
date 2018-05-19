package com.investedu.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.investedu.model.Message;
import com.investedu.model.Users;
import com.investedu.repositories.UserRepo;

@RestController
public class AuthenticationService {
	
	@Autowired
	UserRepo userRepo;

	@RequestMapping(
			value = "/v1.0/register",
			method = RequestMethod.POST )
	@ResponseBody
	public Message registerUser(@RequestBody Users input) {
		System.out.println(input.getName());
		Message msg = new Message();
		
		try {
			userRepo.save(input);
			msg.setMessage("success");
		}catch(Exception e) {
			msg.setMessage("Failed to update - " + e.getMessage());
			System.out.println(e.getMessage());
		}
		
		return msg;
	}
}
