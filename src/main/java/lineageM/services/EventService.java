package lineageM.services;

import java.util.List;

import lineageM.domain.dto.EventRequestDto;
import lineageM.domain.dto.EventResponseDto;

public interface EventService {

	void save(EventRequestDto dto);

	List<EventResponseDto> listAll();

}
