package com.fr.diginamic.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.diginamic.service.HelloService;

/**
 * 
 */
@RestController
@RequestMapping("/hello")
public class HelloRestController {
	
	@Autowired
	HelloService helloService;


	@GetMapping
	public String sayHello() {
		return helloService.getSalutation();
	}
}
