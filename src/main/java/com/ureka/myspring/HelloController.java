package com.ureka.myspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	private int cnt=0;
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Spring "+cnt++;
	}
}
