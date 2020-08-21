package lineageM.controller;


import java.io.File;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.JoinDto;
import lineageM.domain.dto.LoginDto;
import lineageM.domain.dto.SessionUser;
import lineageM.services.MemberService;

@Controller
public class ViewController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/sign/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
	//로그인 처리
	@PostMapping("/sign/login")
	public ModelAndView login(LoginDto dto) {
		//System.out.println(dto);
		//입력된 정보를 DB에서 체크하고..일치하는 데이터가 있으면 로그인처리..아니면 메시지출력
		ModelAndView mv=memberService.loginCheck(dto);
		return mv;
	}
	//로그인 페이지 이동
	@GetMapping("/sign/login")
	public String login() {
		return "/sign/login";
	}
	//회원가입 페이지 이동
	@GetMapping("/sign/join")
	public String join() {
		return "/sign/join";
	}
	//회원가입 처리
	@PostMapping("/sign/join")
	public ModelAndView join(JoinDto dto, HttpServletRequest request) {
		//user_ip 세팅
		dto.setUser_ip(request.getRemoteAddr());
		ModelAndView mv=memberService.save(dto);
		return mv;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<EventListDto> eventList=new Vector<EventListDto>();
		eventList.add(new EventListDto(
				"url(\"/images/event/l1.jpg\")",
				"<디온 공성전>",
				"url(\"/images/event/t1.jpg\")",
				"공성전 가이드북",
				"url(\"/images/event/b1.jpg\")",
				"디온 공선전 이벤트 통합 안내"
				));
		eventList.add(new EventListDto(
				"url(\"/images/event/l2.jpg\")",
				"디온 훈련소",
				"url(\"/images/event/t2.jpg\")",
				"회색기사단의 습격",
				"url(\"/images/event/b2.jpg\")",
				"전쟁의 시작"
				));
		eventList.add(new EventListDto(
				"url(\"/images/event/l3.jpg\")",
				"최초 성의 주인",
				"url(\"/images/event/t3.jpg\")",
				"내복단의 이름으로",
				"url(\"/images/event/b3.jpg\")",
				"행운의 황금 주사위"
				));
		eventList.add(new EventListDto(
				"url(\"/images/event/l4.jpg\")",
				"리니지2M X KT결제 할인 프로모션",
				"url(\"/images/event/t4.jpg\")",
				"WELCOME 풍요의 시대 데일리 혜택",
				"url(\"/images/event/b4.jpg\")",
				"빛나는 영웅 전설"
				));
		eventList.add(new EventListDto(
				"url(\"/images/event/l5.jpg\")",
				"영웅으로 가는 길",
				"url(\"/images/event/t5.jpg\")",
				"체,체,체,체,체인지",
				"url(\"/images/event/b5.jpg\")",
				"기기등록 후 안전하게 플레이하세요!"
				));
		
		model.addAttribute("eventList", eventList);
		
		File file=new File("./");
		//System.out.println(file.getAbsolutePath());
		model.addAttribute("path", file.getAbsolutePath());
		return "/index";
	}
}
