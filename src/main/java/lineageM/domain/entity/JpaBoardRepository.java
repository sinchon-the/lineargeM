package lineageM.domain.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaBoardRepository  extends JpaRepository<JpaBoard, Long> {

}
