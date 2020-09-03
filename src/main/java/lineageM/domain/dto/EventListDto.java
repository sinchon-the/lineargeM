package lineageM.domain.dto;

import lineageM.domain.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EventListDto {
	private Long no;
	private String l_text;
	private String t_text;
	private String b_text;
	
	private String l_url;
	private String t_url;
	private String b_url;
	
	private String used;

	public EventListDto(Event event) {
		
		this.no = event.getNo();
		this.l_text = event.getL_text();
		this.t_text = event.getT_text();
		this.b_text = event.getB_text();
		this.l_url = event.getL_url();
		this.t_url = event.getT_url();
		this.b_url = event.getB_url();
		this.used = event.getUsed();
	}
	
	
	
}
