package lineageM.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import lineageM.domain.dto.JpaDto;
import lineageM.services.JpaService;
import lineageM.utils.ClientIP;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Controller
public class JpaController {
	
	//@Autowired
	private final JpaService service;
	
	@GetMapping("/jpa/list")
	public String jpaList(Model model) {
		//DB에서 list갖고와서. /jpa/list.html 페이지로 보냅시다.
		List<JpaDto> list=service.list();
		model.addAttribute("jpaList", list);
		return "/jpa/list";
	}
	
	//ㅍㅔ이지 이동
	@GetMapping("/jpa/write")
	public String write() {
		return "/jpa/write";///jpa/write.html
	}
	
	@PostMapping("/jpa/write")
	public String write(JpaDto dto, HttpServletRequest request) {
		//System.out.println(dto);
		
		//db에 dto를 저장하고해라 
		//dto.setUser_ip(request.getRemoteAddr());
		dto.setUser_ip(ClientIP.getClientIP(request));
		service.save(dto);
		log.debug("dto :"+dto);
		//저장후 페이지이동
		return "redirect:/jpa/list";// /jpa/list 재 요청
	}
	@GetMapping("/jpa/{no}")// /jpa/2, /jpa/1 , /jpa/3
	public String detail(@PathVariable Long no, Model model) {
		
		//no 의 해당하는 상세정보 갖고와
		JpaDto detail=service.getDetail(no);//한개
		//상세정보 속성저장하고
		model.addAttribute("dto", detail);
		//ㅍㅔ이지 이동
		return "/jpa/detail";
	}
	
	@PostMapping("/jpa/edit")
	public String edit(JpaDto dto) {
		//no, subject, content
		//수정해주세요
		service.edit(dto);
		//수정후 상세페이지로 이동 : 수정됬는지 확인 
		return "redirect:/jpa/"+dto.getNo();
	}
	
	@GetMapping("/jpa/delete/{no}")
	public String delete(@PathVariable Long no) {
		//삭제처리하세요
		service.delete(no);
		return "redirect:/jpa/list";
	}
	
}
