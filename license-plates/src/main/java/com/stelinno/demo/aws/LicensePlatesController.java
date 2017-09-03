package com.stelinno.demo.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LicensePlatesController {

	@RequestMapping("/")
	public String home() {
	    return "This is the root of the License Plate Micro Service";
	}
	
	@RequestMapping("/lookup")
	public @ResponseBody LicensePlate lookup(String licensePlateNo) {
	    LicensePlate licensePlate = new LicensePlate();
	    
	    if(licensePlateNo.equalsIgnoreCase("bj68632")) {
	    	licensePlate.owner = "Michael Sundgaard";
	    	licensePlate.carManufacturer = "BMW";
	    	licensePlate.carModel = "318D Touring UT31";
	    	licensePlate.carYear = "2012";
	    }	    
	    else if(licensePlateNo.equalsIgnoreCase("bj68631")) {
	    	licensePlate.owner = "Nicolas Büchert";
	    	licensePlate.carManufacturer = "Skoda";
	    	licensePlate.carModel = "Octavia";
	    	licensePlate.carYear = "2013";
	    }	    
	    
	    return licensePlate;
	}
	
	@RequestMapping("/_ah/health")
	public String healthy() {
		// Message body required though ignored
	    return "Still surviving.";
	}
	
	@RequestMapping("/meta/version")
	public String version() {
		// Message body required though ignored
	    return "V1.0.1.20170808-1337";
	}

	public static void main(String[] args) {
		SpringApplication.run(LicensePlatesController.class, args);
	}	
}
