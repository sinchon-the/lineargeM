package lineageM.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface JoinRepository extends JpaRepository<Join, Long>{

	
	Optional<Join> findByEmailAndPw(String email, String pw);

	Optional<Join> findByEmail(String email);

}
