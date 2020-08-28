package lineageM.services;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lineageM.domain.dto.EventDto;
import lineageM.domain.dto.EventListDto;
import lineageM.domain.dto.EventPageDto;
import lineageM.domain.entity.EventList;
import lineageM.domain.entity.EventListRepository;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventListRepository repository;
	
	@Override
	public void reg(EventDto dto) {
		repository.save(dto.toEntity());// EventList
	}

	@Override
	public List<EventListDto> getEventList() {
		
		Sort sort=Sort.by(Direction.ASC, "no");
		List<EventList> list=repository.findAllByUsed("on",sort);
		
		List<EventListDto> dtos=new Vector<EventListDto>();
		for(EventList el:list) {
			EventListDto dto=new EventListDto(el);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public List<EventPageDto> getList() {
		Sort sort=Sort.by(Direction.ASC, "no");
		List<EventList> entityList=repository.findAll(sort);
		
		List<EventPageDto> dtos=new Vector<>();
		for(EventList el: entityList) {
			//EventList-->EventPageDto
			EventPageDto dto=new EventPageDto(el);
			dtos.add(dto);
		}
		return dtos;
	}

	@Transactional
	@Override
	public void toggleUsed(Long no) {
		
		repository.findById(no)
		//	검색한 데이터가 수정이 필요한경우 .map()사용
				.map(e->e.toggleUsed(e.getUsed()))
				.orElse(null);
	}

}
