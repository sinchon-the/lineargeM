package lineageM.domain.dto;

import java.util.List;
import java.util.Vector;

import lineageM.domain.entity.Event;
import lombok.Data;

@Data
public class EventRequestDto {
	private String l_text;
	private String t_text;
	private String b_text;
	
	private List<String> urls=new Vector<>();
	
	public Event toEntity() {
		//return new Event(l_text, t_text, b_text, urls.get(0), urls.get(1), urls.get(2));
		return Event.builder()
				.l_text(l_text)
				.t_text(t_text)
				.b_text(b_text)
				.l_url(urls.get(0))
				.t_url(urls.get(1))
				.b_url(urls.get(2))
				.build();
	}
}
