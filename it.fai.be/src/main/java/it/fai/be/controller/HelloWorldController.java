package it.fai.be.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloWorldController {

	@RequestMapping("/home")
	public String index() {
		return "Welcome to BE Application!";
	}

}