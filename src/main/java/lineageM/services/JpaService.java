package lineageM.services;

import java.util.List;

import lineageM.domain.dto.JpaDto;

public interface JpaService {

	List<JpaDto> list();

	void save(JpaDto dto);

	JpaDto getDetail(Long no);

	void edit(JpaDto dto);

	void delete(Long no);

}
