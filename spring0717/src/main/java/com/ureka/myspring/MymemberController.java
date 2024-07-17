package com.ureka.myspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.Setter;

@Controller
public class MymemberController {
	@Autowired
	private MymemberRepository mymemberRepo; // MymemberRepository: SQL 처리모듈

	@GetMapping("/mymember/list")
	@ResponseBody
	public String list() {
		long cnt = mymemberRepo.count();
		return "mymember list "+cnt;
	}
}
