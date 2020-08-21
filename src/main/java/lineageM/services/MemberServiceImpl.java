package lineageM.services;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.JoinDto;
import lineageM.domain.dto.LoginDto;
import lineageM.domain.dto.SessionUser;
import lineageM.domain.entity.Join;
import lineageM.domain.entity.JoinRepository;
import lombok.RequiredArgsConstructor;

//스프링에서 Bean을 주입(DI) @Autowired 필드 , setter,  생성자
//가장권장하는 방식은 생성자로 Bean 객체생성
@RequiredArgsConstructor//final 필드만 적용 
@Service
public class MemberServiceImpl implements MemberService{
	private final HttpSession httpSession;
	private final JoinRepository joinRepository;
	
	@Override
	public ModelAndView save(JoinDto dto) {
		//현재 등록할 email이 중복인지 체크
		Optional<Join> check=joinRepository.findByEmail(dto.getEmail());
		ModelAndView mv=new ModelAndView("/sign/login");
		if(!check.isPresent()) {
			joinRepository.save(dto.toEntity());
			mv.addObject("welcome_msg", "NC홈페이지 가입을 축하합니다.<br>"+dto.getEmail() +"계정으로 가입되었습니다.");
		}else {
			mv.addObject("log_msg", "이미 가입한 email입니다.<br>"+dto.getEmail() +"확인후 이용 바랍니다.");
		}
		return mv;
	}

	@Override
	public ModelAndView loginCheck(LoginDto dto) {
		//
		Optional<Join> result=joinRepository.findByEmailAndPw(dto.getEmail(), dto.getPw());
		ModelAndView mv=new ModelAndView();
		if(result.isPresent()) {
			//System.out.println("로그인 유저 : "+user.getNick_name());
			Join user=result.get();
			SessionUser log_user=new SessionUser(user);
			//System.out.println(log_user);
			httpSession.setAttribute("user", log_user);
			//HttpServletRequest request=
			//((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
			//request.setAttribute("user", new SessionUser(user));
			
			mv.setViewName("redirect:/");
		}else {
			//System.out.println("비회원");
			mv.setViewName("/sign/login");//sign.login.html
			mv.addObject("log_msg", "계정 또는 비밀번호가 일치하지 않습니다.<br>" + 
					"다시 시도해 주세요.");
		}
		return mv;
	}

	

}
