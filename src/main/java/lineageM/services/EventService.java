package lineageM.services;

import java.util.List;

import lineageM.domain.dto.EventDto;
import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventPageDto;

public interface EventService {

	void reg(EventDto dto);

	List<EventListDto> getEventList();

	List<EventPageDto> getList();

	void toggleUsed(Long no);

}
