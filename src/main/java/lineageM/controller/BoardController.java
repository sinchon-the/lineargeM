package lineageM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.BoardDto;
import lineageM.services.BoardService;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class BoardController {
	
	@Autowired
	private  BoardService boardService;
	
	@GetMapping("/board/list")
	public ModelAndView board(ModelAndView mv) {
		//DB에가서 데이터 읽어오자
		List<BoardDto> boardList=boardService.selectList();
		mv.setViewName("/board/list");//html 경로(templates 아래)
		mv.addObject("boardList", boardList);//page에 갖고갈 data
		return mv;
	}
	@GetMapping("/board/{no}")//  /board/2 , /board/1, /board/3
	public ModelAndView detail(@PathVariable int no, ModelAndView mv) {
		// no의 게시글정보를 갖고와서 detail페이지에 보내서 보여준다.
		mv.setViewName("/board/detail");///board/detail.html
		
		BoardDto detail=boardService.detail(no);
		mv.addObject("dto", detail);//페이지로 넘어갈 detail data
		return mv;
	}
	
	@GetMapping("/board/write")
	public String write() {
		return "/board/write";
	}
	
	@PostMapping("/board/write")
	public String write(BoardDto dto) {
		log.info("dto :"+dto);
		boardService.insert(dto);
		return "redirect:/board/list";//요청주소를 /board/list 변경해서 요청
		//return "/board/list";
	}
	
	@PostMapping("/board/edit")
	public String edit(BoardDto dto) {
		//요청된 data를 DB로 보내서 수정처리하세요..
		log.info("dto :"+dto);
		boardService.edit(dto);
		return "redirect:/board/"+dto.getNo();
	}
	@GetMapping("board/delete/{no}")
	public String delete(@PathVariable int no) {
		//요청된 no의 data를 삭제하세요
		log.info("no :"+no);
		boardService.delete(no);
		return "redirect:/board/list";
	}
}
