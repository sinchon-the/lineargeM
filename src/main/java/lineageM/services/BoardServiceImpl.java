package lineageM.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mysql.cj.jdbc.ClientInfoProvider;

import lineageM.domain.dto.BoardDto;
import lineageM.mapper.BoardMapper;
import lineageM.utils.ClientIP;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private HttpServletRequest request;
	
	@Override
	public List<BoardDto> selectList() {
		// DB연결
		
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insert(BoardDto dto) {
		
		//dto.setUser_ip(request.getRemoteAddr());
		dto.setUser_ip(ClientIP.getClientIP());
		
		boardMapper.insert(dto);
		
	}

	@Override
	public BoardDto detail(int no) {
		
		return boardMapper.detail(no);
	}

	@Override
	public void edit(BoardDto dto) {
		
		boardMapper.edit(dto);
	}

	@Override
	public void delete(int no) {
		
		boardMapper.delete(no);
		
	}

}
