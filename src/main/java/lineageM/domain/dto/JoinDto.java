package lineageM.domain.dto;

import lineageM.domain.entity.Join;
import lombok.Data;

@Data
public class JoinDto {
	private String email;
	private String pw;
	private String user_ip;
	
	public Join toEntity(){
		return Join.builder().email(email).pw(pw).user_ip(user_ip).build();
	}
}
