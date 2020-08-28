package lineageM.services;

import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lineageM.domain.dto.JpaDto;
import lineageM.domain.entity.JpaBoard;
import lineageM.domain.entity.JpaBoardRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class JpaServiceImpl implements JpaService{
	
	@Autowired
	private JpaBoardRepository repository;
	
	@Override
	public List<JpaDto> list() {
		//DB에서 list갖고와서. 
		List<JpaBoard> result=repository.findAll();
		List<JpaDto> list=new Vector<>();
		
		for(JpaBoard jpaBoard : result) {
			JpaDto dto=new JpaDto(jpaBoard);
			list.add(dto);
		}
		
		return list;
	}
	@Override
	public void save(JpaDto dto) {
		//dto->entity로 변환
		repository.save(dto.toEntity());
	}
	
	@Transactional
	@Override
	public JpaDto getDetail(Long no) {
		/*
		Optional<JpaBoard> op=repository.findById(no);
		if(op.isPresent()) {
			JpaBoard jpaBoard=op.get();
			return new JpaDto(jpaBoard);
		}else {
			return null;
		}
		*/
		//repository.findById(no) Optional 리턴시
		// JpaBoard entity가 나올것이라 단정하고 코딩할수있다.
		// null인경우에 처리는 orElse()
		/*
		JpaBoard result=repository.findById(no)
				.map(e->e.countIncrement())//수정이 필요한경우 
				.orElse(null);
		*/
		JpaBoard result=repository.findById(no)
				.orElse(null);
		
		result.countIncrement();//조회수 증가 : data 수정
		
		return new JpaDto(result);
		
	}
	
	@Transactional
	@Override
	public void edit(JpaDto dto) {
		
		//DB의 원래 데이터
		JpaBoard board=repository.findById(dto.getNo()).orElse(null);
		//수정할 데이터만 수정
		board.update(dto.getSubject(), dto.getContent());
		log.debug("dto : "+dto);
		//repository.save( board );
		
	}
	@Override
	public void delete(Long no) {
		repository.deleteById(no);		
	}

}
