package lineageM.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.BoardDto;
import lineageM.domain.dto.SpringDto;
import lineageM.mapper.SpringMapper;
import lineageM.utils.ClientIP;

@Service
public class SpringServideImpl implements SpringService{

	@Autowired
	SpringMapper springMapper;
	@Override
	public List<BoardDto> selectList() {
		return springMapper.selectList();
	}
	@Override
	public void insert(SpringDto dto) {
		dto.setUser_ip(ClientIP.getClientIP());
		springMapper.insert(dto);
	}
	@Override
	public SpringDto detail(int no) {
		springMapper.countUp(no);
		return springMapper.detail(no);
	}

}
