package lineageM.services;

import java.util.List;

import lineageM.domain.dto.BoardDto;
import lineageM.domain.dto.SpringDto;

public interface SpringService {

	List<BoardDto> selectList();

	void insert(SpringDto dto);

	SpringDto detail(int no);

}
