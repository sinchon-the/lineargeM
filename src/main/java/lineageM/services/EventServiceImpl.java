package lineageM.services;

import java.util.List;
import java.util.Vector;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lineageM.domain.dto.EventListDto;
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
	public List<EventListDto> listAll() {
		Sort sort=Sort.by(Direction.ASC,"no");
		List<Event> result=repository.findAll(sort);
		
		List<EventListDto> list=new Vector<>();
		
		for(Event event :result) {
			EventListDto dto=new EventListDto(event);
			list.add(dto);
		}
		
		return list;
	}

	@Override
	public List<EventResponseDto> listAllByUsed() {
		List<Event> result=repository.findAllByUsed("on");
		
		List<EventResponseDto> list=new Vector<>();
		
		for(Event event :result) {
			EventResponseDto dto=new EventResponseDto(event);
			list.add(dto);
		}
		return list;
	}
	
	@Transactional
	@Override
	public void used(Long no) {
		//수정하기위해서
		//일단은 수정할 목록을 불러오자
		//수정이 필요하면 map()수정하자
		repository.findById(no)
		.map(entity->entity.usedToggle()).orElse(null);
	}

	@Override
	public void delete(Long no) {
		
		repository.deleteById(no);
		
	}

	@Transactional
	@Override
	public void edit(EventListDto dto) {
		
		repository.findById(dto.getNo())
				.map(e->e.update(dto) )
				.orElse(null);
		
		//repository.save(dto.toEntity());
		
	}
	
}
