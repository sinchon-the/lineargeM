package lineageM.domain.dto;

import lineageM.domain.entity.Event;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class EventResponseDto {
	private String l_text;
	private String t_text;
	private String b_text;
	
	private String l_url;
	private String t_url;
	private String b_url;
	
	public EventResponseDto(Event event) {
		this.l_text = event.getL_text();
		this.t_text = event.getB_text();
		this.b_text = event.getB_text();
		this.l_url = "url('/upload/event/"+event.getL_url()+"')";
		this.t_url = "url('/upload/event/"+event.getT_url()+"')";
		this.b_url = "url('/upload/event/"+event.getB_url()+"')";
	}
	
	
}
