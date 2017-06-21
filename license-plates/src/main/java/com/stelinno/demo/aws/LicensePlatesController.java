package com.stelinno.demo.aws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LicensePlatesController {

	@RequestMapping("/")
	public String home() {
	    return "This is the root of the License Plate Micro Service";
	}
	
	@RequestMapping("/_ah/health")
	public String healthy() {
		// Message body required though ignored
	    return "Still surviving.";
	}

	public static void main(String[] args) {
		SpringApplication.run(LicensePlatesController.class, args);
	}	
}
