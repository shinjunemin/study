package kr.co.study.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.SignInService;
import kr.co.study.repository.SignInMapper;
import kr.co.study.web.dto.JoinDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
public class SignInController {	//수정되면 안되고 다른 컨트롤러 만들어야함.(SignInService도 마찬가지)
	
	@Autowired
	private final PasswordEncoder passwordEncoder;
	
	@Autowired
	private final SignInMapper mapperService;
	
	@Autowired
	private final SignInService service;
	
	@GetMapping(value = "/join") //인증없이 들어갈 수 없기때문에 예외처리를 해줘야함 antMatchers("/error", "/join.do", "/userSave.do", "/h2-console/")
	public String join(Model model, JoinDTO joinDTO) {// joinDTO를 그대로 들고옴
		return "thymeleaf/page/join";
	}
	
	@PostMapping(value = "/userSave")
	public String userSave(@Valid @ModelAttribute JoinDTO joinDTO, BindingResult bindingResult) {
		//@RequiredArgsConstructor : final이 붙은 필드에 대한 생성자를 생성해준다.
		// <-> @AllArgsConstructor : 필드값을 모두 포함한 생성자를 자동 생성해준다.
		//@Valid : 벨리데이션을 태우기 위해 필요함
		//@ModelAttribute : contentType, 넘어오는 방식에따라 바뀔수 있음.
		//BindingResult : 벨리데이션 결과를 받을 수 있는 함수
		if (bindingResult.hasErrors()) { // 해석 : 에러가 있으면 "thymeleaf/page/join"로 보냄 
			return "thymeleaf/page/join";
		}
		else {
			String sId = joinDTO.getId();
			String sName = joinDTO.getName();
			String sPassword = passwordEncoder.encode(joinDTO.getPassword());
			JoinDTO setDto = new JoinDTO(sId, sName, sPassword);
			service.insertUser(setDto);
			//service.insertUser(joinDTO);
		}

		//return "thymeleaf/signin";
		
		return "redirect:/";

	}
	
	@RequestMapping("/signin")
	public String signin() {
		return "thymeleaf/signin";
	}
}
