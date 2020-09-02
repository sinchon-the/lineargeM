package lineageM.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.EventRequestDto;
import lineageM.domain.dto.EventResponseDto;
import lineageM.domain.entity.Event;
import lineageM.domain.entity.EventRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository repository;
	
	@Override
	public void save(EventRequestDto dto) {
		
		repository.save(dto.toEntity());
		
	}

	@Override
	public List<EventResponseDto> listAll() {
		List<Event> result=repository.findAllByUsed("on");
		
		List<EventResponseDto> list=new Vector<>();
		
		for(Event event :result) {
			EventResponseDto dto=new EventResponseDto(event);
			list.add(dto);
		}
		return list;
	}
	
}
