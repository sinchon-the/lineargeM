package lineageM.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.BoardDto;
import lineageM.domain.dto.SpringDto;
import lineageM.services.SpringService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class SpringController {
	
	@Autowired
	private SpringService service;
	
	@GetMapping("/spring/list")
	public ModelAndView board(ModelAndView mv) {
		//DB에가서 데이터 읽어오자
		List<BoardDto> list=service.selectList();
		mv.setViewName("/spring/list");//html 경로(templates 아래)
		mv.addObject("list", list);//page에 갖고갈 data
		mv.addObject("today", LocalDate.now());
		return mv;
	}
	
	@GetMapping("/spring/write")
	public String write() {
		return "/spring/write";
	}
	
	@PostMapping("/spring/write")
	public String write(SpringDto dto) {
		log.info("dto :"+dto);
		service.insert(dto);
		return "redirect:/spring/list";//요청주소를 /board/list 변경해서 요청
	}
	
	@GetMapping("/spring/{no}")//  /board/2 , /board/1, /board/3
	public ModelAndView detail(@PathVariable int no, ModelAndView mv) {
		// no의 게시글정보를 갖고와서 detail페이지에 보내서 보여준다.
		mv.setViewName("/spring/detail");///board/detail.html
		
		SpringDto detail=service.detail(no);
		mv.addObject("dto", detail);//페이지로 넘어갈 detail data
		return mv;
	}
	
	/*
	
	
	
	
	
	
	@PostMapping("/board/edit")
	public String edit(BoardDto dto) {
		//요청된 data를 DB로 보내서 수정처리하세요..
		log.info("dto :"+dto);
		service.edit(dto);
		return "redirect:/board/"+dto.getNo();
	}
	@GetMapping("board/delete/{no}")
	public String delete(@PathVariable int no) {
		//요청된 no의 data를 삭제하세요
		log.info("no :"+no);
		service.delete(no);
		return "redirect:/board/list";
	}
	*/

}
