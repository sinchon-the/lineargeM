package lineageM.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import lineageM.domain.dto.JoinDto;
import lineageM.domain.dto.LoginDto;

public interface MemberService {

	ModelAndView save(JoinDto dto);

	ModelAndView loginCheck(LoginDto dto);

}
