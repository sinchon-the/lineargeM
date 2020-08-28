package lineageM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lineageM.domain.dto.BoardDto;

@Mapper
public interface BoardMapper {
	//xml의 namespace랑  BoardMapper의 경로랑 일치
	// BoardMapper selectBoardList 는 xml의 id와 일치
	List<BoardDto> selectBoardList();

	void insert(BoardDto dto);

	BoardDto detail(int no);

	void edit(BoardDto dto);

	void delete(int no);
}
