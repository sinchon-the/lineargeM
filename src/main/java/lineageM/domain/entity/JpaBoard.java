package lineageM.domain.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//insert 쿼리시에 null인  컬럼은 쿼리작성시 빼주세요..
//update 쿼리시에 변경되지 않은 컬럼은 쿼리작성시 빼주세요..
@DynamicUpdate
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "jpa_board")
@Entity
public class JpaBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//mysql auto_increment
	private Long no;
	
	//@Column(length = 500, nullable = false) 
	//길이정보 mysql은 적용되나 mariaDB에서 적용않되서..	
	@Column(columnDefinition="varchar(500)", nullable = false)
	private String subject;
	
	private int count;
	
	private String writer;
	
	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime reg_date;
	@Column(nullable = false)
	private String user_ip;
	@Column(columnDefinition = "TEXT" , nullable = false)
	private String content;
	
	@Builder
	public JpaBoard(String subject, String writer, String user_ip, String content) {
		this.subject = subject;
		this.writer = writer;
		this.user_ip = user_ip;
		this.content = content;
	}
	
	public JpaBoard update(String subject, String content) {
		this.subject=subject;
		this.content=content;
		return this;
	}
	
	public JpaBoard countIncrement() {
		this.count++;
		return this;
	}
	
	

}
