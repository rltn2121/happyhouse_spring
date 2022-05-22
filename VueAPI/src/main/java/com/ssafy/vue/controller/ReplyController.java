package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.Reply;
import com.ssafy.vue.service.ReplyService;

@RestController
@RequestMapping("/reply")
public class ReplyController {

	private static final Logger logger = LoggerFactory.getLogger(ReplyController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private ReplyService replyService;
	
	@GetMapping("{aptId}")
	public ResponseEntity<List<Reply>> selectReply(@PathVariable int aptId){
		logger.debug("selectReply - 호출");
		
		return new ResponseEntity<List<Reply>>(replyService.selectReply(aptId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> insertReply(@RequestBody Reply reply) {
		logger.debug("insertReply - 호출");
		
		if (replyService.insertReply(reply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@PutMapping("{replySeq}")
	public ResponseEntity<String> updateReply(@RequestBody Reply reply) {
		logger.debug("updateReply - 호출");
		
		if (replyService.updateReply(reply)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("{replySeq}")
	public ResponseEntity<String> deleteReply(@PathVariable int replySeq) {
		logger.debug("deleteReply - 호출");
		
		if (replyService.deleteReply(replySeq)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
