package lineageM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lineageM.domain.dto.EventResponseDto;
import lineageM.services.EventService;


@Controller
public class ViewController {
	
	@Autowired
	private EventService service;
	
	@GetMapping("/")
	public String index(Model model) {
		
		List<EventResponseDto> list=service.listAllByUsed();
		model.addAttribute("eventList", list);
		return "/index";
	}
}
