package lineageM.domain.dto;

import java.util.List;
import java.util.Vector;

import lineageM.domain.entity.EventList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventDto {
	
	private String l_text;
	private String t_text;
	private String b_text;
	private List<String> urls=new Vector<String>();
	
	public EventList toEntity() {
		return EventList.builder()
				.l_text(l_text)
				.t_text(t_text)
				.b_text(b_text)
				.l_url(urls.get(0))
				.t_url(urls.get(1))
				.b_url(urls.get(2))
				.build();
	}
	
}
