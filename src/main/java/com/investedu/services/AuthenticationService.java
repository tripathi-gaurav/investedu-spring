package com.investedu.services;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	UserRepo userRepo;

	@RequestMapping(value = "/v1.0/register", method = RequestMethod.POST)
	@ResponseBody
	public Message registerUser(@RequestBody Users input) {
		System.out.println(input.getName());
		Message msg = new Message();
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(input.getPassword());
		input.setPassword(hashedPassword);

		try {
			userRepo.save(input);
			msg.setMessage("success");
		} catch (Exception e) {
			msg.setMessage("Failed to update - " + e.getMessage());
			logger.debug("Error registering user: " + e.getMessage());
		}

		return msg;
	}

	@RequestMapping(value = "/v1.0/login", method = RequestMethod.POST)
	@ResponseBody
	public Message login(@RequestBody Users input, HttpSession session) {

		Message msg = new Message();
		msg.setMessage("under dev");

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		
		try {
			logger.info(logger.getName() + " :: " +input.getUsername() + " " + input.getPassword());
			System.out.println((input.getUsername() + " " + input.getPassword()));
			//Users userFound = userRepo.findByUsernameAndPassword(input.getUsername(), input.getPassword());
			Users userFound = userRepo.findByUsername(input.getUsername());
			if( userFound == null ) {
				msg.setMessage("Login failed. Check username and/or password.");
			}else {
				if(passwordEncoder.matches(input.getPassword(), userFound.getPassword())) {
					msg.setMessage("success");
					session.setAttribute("currentUser", userFound);
				}else {
					msg.setMessage("Login failed. Check username and/or password.");
				}
			}
		}catch(Exception e) {
			msg.setMessage("Login failed. Check username and/or password." + e.getMessage());
			logger.error(logger.getName() + " :: " + "Error querying database: " + e.getMessage());
		}

		return msg;
	}
	
	@RequestMapping(value = "/v1.0/logout", method = RequestMethod.POST)
	@ResponseBody
	public Message logout(@RequestBody Users input, HttpSession session) {
	
		Message msg = new Message();
		msg.setMessage("under dev");
		try {
			session.invalidate();
			msg.setMessage("success");
		}catch(Exception e){
			msg.setMessage("Oops. Something went wrong.");
			logger.error(logger.getName() + " :: " + msg.getMessage() + " " + e.getStackTrace());
		}
		return msg;
	}
	
}
