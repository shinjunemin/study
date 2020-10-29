package kr.co.study.web.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.study.application.BoardService;
import kr.co.study.domain.Account;
import kr.co.study.domain.Board;
import kr.co.study.web.dto.BoardDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("board")
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("list")
	public String list(Model model) {
		
		List<BoardDTO> list = boardService.selectBoardList();
//				.stream()
//				.map(board -> );
//				.collect(Collectors.)
		model.addAttribute("boardList", list);
		
		return "thymeleaf/page/board/list";
	}
	
	@GetMapping("write")
	public String write(@ModelAttribute(name="board") BoardDTO boardDTO) {
		//@ModelAttribute : 
		// name="board" -> write.html의 th:object="${board}"의 board와 매칭 
		return "thymeleaf/page/board/write";
	}
	
	@PostMapping("write")
	public String writeBoard(@Valid @ModelAttribute(name ="board") BoardDTO boardDTO, BindingResult bindingResult, Authentication authentication) {
		
		if(bindingResult.hasErrors()) {
			return "thymleaf/page/board/write";
			
		}
		Account account = (Account) authentication.getPrincipal();
		boardService.insertBoard(boardDTO.toBoard(account));
		
		return "redirect:list";
	}
	
}
