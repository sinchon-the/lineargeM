package lineageM.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lineageM.domain.dto.EventRequestDto;
import lineageM.services.EventService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class EventController {
	
	@Autowired
	private EventService service;
	
	@GetMapping("/event/list")
	public String event() {
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
