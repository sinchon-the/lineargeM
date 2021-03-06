package lineageM.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventRequestDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	
	@Autowired
	private EventService service;
	
	@PostMapping("/event/edit")
	public String edit(EventListDto dto,
			@RequestParam("l_img") MultipartFile l_img,
			@RequestParam("t_img") MultipartFile t_img,
			@RequestParam("b_img") MultipartFile b_img ) throws IllegalStateException, IOException {
		//업로드할 경로
		String path="D:/spring/work/git/lineargeM/src/main/resources/static/upload/event";
		//File dir=new File(path);
		
		if(!l_img.isEmpty()) {
			//dto에 파일이름 저장
			String fileName=l_img.getOriginalFilename();
			dto.setL_url(fileName);
			//업로드
			File file=new File(path,fileName);
			l_img.transferTo(file);
			
		}
		
		if(!t_img.isEmpty()) {
			String fileName=t_img.getOriginalFilename();
			dto.setT_url(fileName);
			File file=new File(path,fileName);
			t_img.transferTo(file);
		}
		
		if(!b_img.isEmpty()) {
			String fileName=b_img.getOriginalFilename();
			dto.setB_url(fileName);
			File file=new File(path,fileName);
			b_img.transferTo(file);
		}
		
		log.debug(dto);
		service.edit(dto);
		return "redirect:/event/list";
	}
	
	@GetMapping("/event/delete/{no}")
	public String delete(@PathVariable Long no) {
		service.delete(no);
		
		return "redirect:/event/list";
	}
	
	@GetMapping("/event/used/{no}")
	public String used(@PathVariable Long no) {
		service.used(no);
		return "redirect:/event/list";
	}
	
	
	@GetMapping("/event/list")
	public String event(Model model) {
		List<EventListDto> list=service.listAll();
		model.addAttribute("list", list);
		return "/event/list";
	}
	
	@GetMapping("/event/reg")
	public String reg() {
		return "/event/reg";
	}
	@PostMapping("/event/reg")
	public String reg(EventRequestDto dto, @RequestParam("files")MultipartFile[] mfs) throws IllegalStateException, IOException {
		
		
		for(MultipartFile mf : mfs) {
			//파일이름을 Dto에 저장
			String fileName=mf.getOriginalFilename();
			dto.getUrls().add(fileName);
			//파일업로드
			String path="D:/spring/work/git/lineargeM/src/main/resources/static";
			//업로드 시킬 디렉토리
			File dir=new File(path,"/upload/event");
			if(!dir.exists()) {//존재하지 않으면
				dir.mkdirs();//디렉토리 생성
			}
			
			File file=new File(dir, fileName);
			mf.transferTo(file);
			//파일업로드 완료
		}
		log.debug(dto);//DB에 저장할 데이터
		service.save(dto);
		
		return "/event/reg";
	}
}
