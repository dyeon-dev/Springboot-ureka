package com.ureka.myspring;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.domain.Sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ureka.myspring.entity.Board;
import com.ureka.myspring.repository.BoardRepository;
import com.ureka.myspring.repository.YaksokRepository;


@Controller
public class BoardController {
	
	@Autowired
	private BoardRepository boardRepo;
	
	@Autowired
	private YaksokRepository ysRepo;
	
	@PostMapping("/board/regist")
	@ResponseBody
	public Map<String,Object> regist(Board bd) {
		Map<String, Object> result = new HashMap<>();
		Date d = new java.util.Date();
		bd.setRegdate(d);
		boardRepo.save(bd);
		result.put("code", "ok");
		return result ;
	}

	
	@GetMapping("/board/count")
	@ResponseBody
	public Map<String, Object> count() {
		Map<String, Object> result = new HashMap<>();
		long count = boardRepo.count();
		//yaksok 테스트 - 약속 갯수 
		long ysCount = ysRepo.count();
		result.put("code", "ok");
		result.put("data", count);
		result.put("data", ysCount);
		return result;
	}


	@GetMapping("/board/list")
	@ResponseBody
	public Map<String, Object> list() {
		Map<String, Object> result = new HashMap<>();
		List<Board> count = boardRepo.findAll(Sort.by(Sort.Direction.DESC,"no"));
		result.put("code", "ok");
		result.put("data", count);
		return result; 
	}

	
	@GetMapping("/board/detail/{no}")
	@ResponseBody
	public Board detail(@PathVariable("no") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Board> ys = boardRepo.findById(no);
		if(ys.isPresent()) {
			Board board = ys.get();
			result.put("code", "ok");
			result.put("code", board);
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다");			
		}
		return ys.get();
	}
	
	@PostMapping("/board/update")
	@ResponseBody
	public Map<String,Object> update(Board ubd) {
		Map<String, Object> result = new HashMap<>();
		Optional<Board> bd = boardRepo.findById(ubd.getNo());
		if(bd.isPresent()) {
			Board board = bd.get();
			board.setTitle(ubd.getTitle());
			board.setContent(ubd.getContent());
			boardRepo.save(board);
			result.put("code", "ok");
		} else {
			result.put("code", "error");
			result.put("message", "없거나 삭제된 번호입니다");
		}
		return result;
	}
	
	@GetMapping("/board/delete/{no}")
	@ResponseBody
	public Map<String,Object> delete(@PathVariable("no") int no) {
		Map<String, Object> result = new HashMap<>();
		Optional<Board> bd = boardRepo.findById(no);
		if(bd.isPresent()) {
			boardRepo.deleteById(no);
			result.put("code", "ok");
		}else {
			result.put("code", "error");
			result.put("message","없거나 삭제된 번호입니다" );
		}
		return result ;
	}

}
