package lineageM.domain.dto;

import java.time.LocalDateTime;

import lineageM.domain.entity.JpaBoard;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class JpaDto {
	
	private Long no;
	private String subject;//
	private int count;
	private String writer;//
	private LocalDateTime reg_date;
	private String user_ip;
	private String content;//
	
	public JpaDto(JpaBoard jpaBoard) {
		this.no = jpaBoard.getNo();
		this.subject = jpaBoard.getSubject();
		this.count = jpaBoard.getCount();
		this.writer = jpaBoard.getWriter();
		this.reg_date = jpaBoard.getReg_date();
		this.user_ip = jpaBoard.getUser_ip();
		this.content = jpaBoard.getContent();
	}
	
	public JpaBoard toEntity() {
		//
		return new JpaBoard(subject, writer, user_ip, content);
		/*
		return JpaBoard.builder()
				.subject(subject)
				.content(content)
				.user_ip(user_ip)
				.writer(writer).build();
				//*/
	}
	
	

}
