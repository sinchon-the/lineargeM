package lineageM.domain.entity;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventListRepository extends JpaRepository<EventList, Long>{

	List<EventList> findAllByUsed(String string, Sort sort);

}
