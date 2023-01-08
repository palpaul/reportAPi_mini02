package com.rahul.reportapi.asController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MySecurityController {

	
	@GetMapping("/")
	public String welcome() {
		return "Hey welcome to SBI Bank";
	}
	
	@GetMapping("/transfer")
	public String transfer() {
		return "Fund Transfer Initiated";
	}
	
	@GetMapping("/balance")
	public String balance() {
		return "your account balance is:- 1000 INR";
	}
	
	
	@GetMapping("/aboutus")
	public String aboutus() {
		return "SBI Bank managing by Central government of India";
	}
	
	
}
