package com.ssafy.vue.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import com.ssafy.vue.dto.MemberDto;
import com.ssafy.vue.dto.MemberResultDto;
import com.ssafy.vue.service.JwtServiceImpl;
import com.ssafy.vue.service.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api("MemberController V1")
@RestController
@RequestMapping("/user")
public class MemberController {

	public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JwtServiceImpl jwtService;

	@Autowired
	private MemberService memberService;

	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) MemberDto memberDto) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			
			// 1. DB에 있는지 확인
			MemberDto loginUser = memberService.login(memberDto);
			System.out.println(loginUser);
			
			if (loginUser != null) {
				// 2. DB에 있으면 JWT 만들기
//				String token = jwtService.create("userid", loginUser.getUserid(), "access-token");// key, data, subject
				String token = jwtService.create("user_seq", loginUser.getUserSeq(), "access-token");// key, data, subject
				logger.debug("로그인 토큰정보 : {}", token);
				
				// 3. RESPONSE에 담기
				resultMap.put("access-token", token);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userSeq}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userSeq") @ApiParam(value = "인증할 회원의 아이디.", required = true) int userSeq,
			HttpServletRequest request) {
		logger.debug("userSeq : {} ", userSeq);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtService.isUsable(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDto memberDto = memberService.userInfo(userSeq);
				System.out.println(memberDto);
				resultMap.put("userInfo", memberDto);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.ACCEPTED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(@RequestBody MemberDto dto, HttpSession session){
		
		MemberResultDto memberResultDto = memberService.userRegister(dto);
		Map<String, String> map = new HashMap<>();
		
		if( memberResultDto.getResult() == 1 ) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{userSeq}")
	public ResponseEntity<MemberResultDto> findById(@PathVariable("userSeq") int userSeq){
		System.out.println("***findById***");
		System.out.println(userSeq);
		
		MemberResultDto dto = memberService.findById(userSeq);
		System.out.println("조회결과: " + dto);
		if(dto != null) {
			return new ResponseEntity<MemberResultDto>(dto, HttpStatus.OK);
		}else {
			return new ResponseEntity<MemberResultDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@DeleteMapping("/{userSeq}")
	public ResponseEntity<Map<String, String>> deleteById(@PathVariable("userSeq") int userSeq){
		System.out.println("****deleteById****");
		System.out.println(userSeq);
		
		MemberResultDto memberResultDto = memberService.deleteById(userSeq);
		Map<String, String> map = new HashMap<>();
		
		if( memberResultDto.getResult() == 1 ) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	 
	
	@PutMapping("/{userSeq}")
	public ResponseEntity<Map<String, String>> update(@RequestBody MemberDto dto){
		System.out.println("****update****");
		System.out.println(dto);
		
		MemberResultDto memberResultDto = memberService.update(dto);
		Map<String, String> map = new HashMap<>();
		
		if( memberResultDto.getResult() == 1 ) {
			map.put("result", "success");
			System.out.println("컨트롤러 -> 수정 성공");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	 
}
