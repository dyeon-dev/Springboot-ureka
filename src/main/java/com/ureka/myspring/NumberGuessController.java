package com.ureka.myspring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NumberGuessController {
	
	private int com_num = 0;//난수저장변수( 1~100 )
	
	@GetMapping("/check_num")
	@ResponseBody
	public String check_num(@RequestParam(value="re") int num) {
		if(com_num == 0) {//난수생성전
			com_num = (int)(Math.random()*100+1);
		}
		if(com_num == num)
			return "정답";
		if( com_num < num)
			return "낮춰주세요";
		return "높여주세요";
	}
}