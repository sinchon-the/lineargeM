package lineageM.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import lineageM.domain.dto.BoardDto;
import lineageM.domain.dto.SpringDto;

@Mapper
public interface SpringMapper {

	List<BoardDto> selectList();

	void insert(SpringDto dto);

	SpringDto detail(int no);

	void countUp(int no);

}
