package lineageM.domain.dto;

import java.io.Serializable;

import lineageM.domain.entity.Join;
import lombok.Getter;
import lombok.ToString;

//인증된 사용자정보 직렬화를 구현하였다.
@ToString
@Getter
public class SessionUser{
	//private static final long serialVersionUID = 1L;
	private String name;
	private String email;
	//private String picture;
	public SessionUser(Join user) {
		this.name = user.getNick_name();
		this.email = user.getEmail();
	}
	
	
}
