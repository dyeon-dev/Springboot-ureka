package com.ureka.myspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@GetMapping
	@ResponseBody
	public String hello() {
		return "HELLO!";
	}
}
