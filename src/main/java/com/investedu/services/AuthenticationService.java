package com.investedu.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.investedu.model.Data;
import com.investedu.model.HistoricalDataQuery;
import com.investedu.model.Message;
import com.investedu.model.Users;
import com.investedu.repositories.UserRepo;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@RestController
public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	UserRepo userRepo;
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/v1.0/register", method = RequestMethod.POST)
	@ResponseBody
	public Message registerUser(@RequestBody Users input) {
		System.out.println(input.getName());
		Message msg = new Message();
		try {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(input.getPassword());
			input.setPassword(hashedPassword);
			userRepo.save(input);
			msg.setMessage("User created successfully");
			msg.setAuth(true);
			Data data = new Data();
			data.setUser(input.getUsername());
			msg.setData(data);
		} catch (Exception e) {
			msg.setMessage("Failed to update - " + e.getMessage());
			msg.setAuth(false);
			logger.debug("Error registering user: " + e.getMessage());
		}
		
		
		return msg;
	}

	@CrossOrigin(origins = "http://localhost:3000")
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
					msg.setMessage("User login success!.");
					msg.setAuth(true);
					Data data = new Data();
					data.setUser(userFound.getUsername());
					msg.setData(data);
					session.setAttribute("currentUser", userFound);
				}else {
					msg.setMessage("Login failed. Check username and/or password.");
				}
			}
		}catch(Exception e) {
			msg.setMessage("Login failed. Check username and/or password." + e.getMessage());
			msg.setAuth(false);
			logger.error(logger.getName() + " :: " + "Error querying database: " + e.getMessage());
		}

		return msg;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/v1.0/logout", method = RequestMethod.POST)
	@ResponseBody
	public Message logout(@RequestBody Users input, HttpSession session) {
	
		Message msg = new Message();
		msg.setMessage("under dev");
		try {
			session.invalidate();
			msg.setMessage("Logout successful");
			msg.setAuth(false);
			Data data = new Data();
			data.setUser(null);
			msg.setData(data);
		}catch(Exception e){
			msg.setMessage("Oops. Something went wrong.");
			logger.error(logger.getName() + " :: " + msg.getMessage() + " " + e.getStackTrace());
		}
		return msg;
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping(value = "/v1.0/historicalData", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String getHistoricalTradingData(@RequestBody HistoricalDataQuery input, HttpSession session) {
		String queryURL = "https://api.iextrading.com/1.0/stock/market/batch?symbols=";
		Message msg = new Message();
		msg.setMessage("under dev");
		
		List<String> companies = input.getComapnies();
		StringBuilder sb = new StringBuilder(queryURL);
		for(String company : companies) {
			sb.append(company);
			sb.append(",");
		}
		
		List<String> types = input.getTypes();
		if(types.size() > 0) {
			sb.append("&types=");
			for(String type : types) {
				sb.append(type);
				sb.append(",");
			}
		}
		
		String range = input.getRange();
		if(range != null && !"".equalsIgnoreCase(range)) {
			sb.append("&range=");
			sb.append(range);
		}
		
		String last = input.getLast();
		if(last != null && !"".equalsIgnoreCase(last)) {
			sb.append("&last=");
			sb.append(last);
		}
		
		
		OkHttpClient client = new OkHttpClient();
		queryURL = sb.toString();
		logger.debug("======" + queryURL);
		
		Response response = null;
		
		String responseToRelay;
		try {
			Request request = new Request.Builder().url(queryURL).build();
			 response = client.newCall(request).execute();
			//System.out.println(response.body().string());
			 responseToRelay = response.body().string();
			
		} catch (Exception e) {
			System.out.println("LOG this: " + e.getMessage());
			responseToRelay = "error";
			e.printStackTrace();
		}
		
		return responseToRelay;
	}
	
}
