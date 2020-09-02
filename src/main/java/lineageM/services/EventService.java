package lineageM.services;

import java.util.List;

import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventRequestDto;
import lineageM.domain.dto.EventResponseDto;

public interface EventService {

	void save(EventRequestDto dto);

	List<EventListDto> listAll();

	List<EventResponseDto> listAllByUsed();

	void used(Long no);

	void delete(Long no);

}
