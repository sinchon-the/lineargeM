package lineageM.domain.entity;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@DynamicInsert//insert시  not null인 column만 sql에 포함
@NoArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "jpa_join")
@Entity
public class Join {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	@Column(nullable = false, unique = true)//not null
	private String email;
	@Column(nullable = false)//not null
	private String pw;
	
	@Column(columnDefinition = "varchar(255) DEFAULT 'NickName'")
	private String nick_name;
	
	@Column(nullable = false)//not null
	private String user_ip;
	
	@CreatedDate// entity가 생성되어 저장될때 시간이 자동 저장
	@Column(nullable = false)//not null
	private LocalDateTime create_date;
	
	@LastModifiedDate// entity의 값을 변경할 때 시간이 자동 저장된다
	private LocalDateTime edit_date;
	
	@Builder
	public Join(String email, String pw, String user_ip, String nick_name) {
		this.email = email;
		this.pw = pw;
		this.user_ip=user_ip;
		this.nick_name=nick_name;
	}
	
	
	

}
