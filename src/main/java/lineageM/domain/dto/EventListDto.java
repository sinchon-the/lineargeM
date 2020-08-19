package lineageM.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventListDto {
	
	private String l_url;
	private String l_text;
	private String t_url;
	private String t_text;
	private String b_url;
	private String b_text;

}
