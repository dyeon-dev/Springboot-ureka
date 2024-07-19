package com.ureka.myspring;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ureka.myspring.entity.Yaksok;


@Controller
public class YaksokController {
	@Autowired
	private YaksokRepository ysRepo;
	
	@PostMapping("/yaksok/regist")
	@ResponseBody
	public String regist(Yaksok ys,
			@RequestParam("_ysdate")String ysdate) {
		System.out.println(ys);
		System.out.println(ysdate);
		LocalDateTime localDateTime = LocalDateTime.parse(ysdate);
		Date d = java.sql.Timestamp.valueOf(localDateTime);
		ys.setYsdate(d);
		ysRepo.save(ys);
		return "ok";
	}
	
	@GetMapping("/yaksok/count")
	@ResponseBody
	public String count() {
		return "" + ysRepo.count();
	}
	
	@RequestMapping("/yaksok/list")
	@ResponseBody
	public List<Yaksok> list() {
		return ysRepo.findAll();
	}
	
	@GetMapping("/yaksok/detail/{no}")
	@ResponseBody
	public Yaksok detail(@PathVariable("no") int no) {
		Optional<Yaksok> ys = ysRepo.findById(no);
		return ys.get();
	}
}