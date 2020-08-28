package lineageM.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventDto;
import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventPageDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	
	@Autowired
	private EventService service;
	
	@GetMapping("/event/used/{no}")
	public String used(@PathVariable Long no) {
		service.toggleUsed(no);
		return "redirect:/event/list";
	}
	
	@GetMapping("/event/reg")
	public String eventReg() {
		return "/event/reg";
	}
	@GetMapping("/event/list")
	public String list(Model model, HttpServletRequest r) {
		//DB에서 
		List<EventPageDto> list=service.getList();
		model.addAttribute("list", list);
		String path=r.getServletContext().getRealPath("/upload/event");
		model.addAttribute("path", path);
		//"list" -->html 의 ${list} 일치
		return "/event/list";
	}
	
	@PostMapping("/event/reg")
	public String eventReg(EventDto dto , @RequestParam("files") MultipartFile[] multipartFile) throws IOException {
		log.debug(">>>>>>> event:" + dto);
		//File dir=new File("D:/spring/workspace/lineageM/src/main/resources/static/images/upload");
		File dir=new File("/baekhwa2015/tomcat/webapps/upload/event");
		if(!dir.exists()) {//dir이 존재하지 않으면
			//directory 생성
			dir.mkdir();
		}
		
		
		
		//경로를 지정해서 넣은경우: 파일업로드
		for(MultipartFile mf:multipartFile) {
			String fileName=mf.getOriginalFilename();
			File file=new File(dir, fileName);
			mf.transferTo(file);//파일업로드
			dto.getUrls().add(fileName);//dto에 파일이름 SET
		}
		
		
		log.debug(">>>>>>> event:" + dto);
		// DB삽입
		service.reg(dto);
		 
		return "redirect:/event/list";
	}
	
}
