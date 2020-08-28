package lineageM.domain.dto;

import lineageM.domain.entity.EventList;
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
	
	public EventList toEntity() {
		return new EventList(l_url, l_text, t_url, t_text, b_url, b_text);
		/*
		return EventList.builder()
				.l_url(l_url).l_text(l_text)
				.t_url(t_url).t_text(t_text)
				.b_url(b_url).b_text(b_text).build();
		*/
	}

	public EventListDto(EventList el) {
		String path="/upload/event/";
		l_url="url('"+path+ el.getL_url()+"')";//url(\"/images/event/l1.jpg\")
		l_text=el.getL_text();
		t_url="url('"+path+  el.getT_url()+"')";
		t_text=el.getT_text();
		b_url="url('"+path+  el.getB_url()+"')";
		b_text=el.getB_text();
		
	}

}
