package de.ollie.carp.maps.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class ErrorMessageDTO {

	private String message;
	private Map<String, String> messageData = Map.of();
	private String messageId;

	@JsonIgnore
	public String[] getMessageDataToStringArray() {
		List<String> l = new ArrayList<>();
		messageData
			.entrySet()
			.stream()
			.sorted((e0, e1) -> e0.getKey().compareTo(e1.getKey()))
			.forEach(e -> {
				l.add(e.getKey());
				l.add(e.getValue());
			});
		return l.toArray(new String[l.size()]);
	}
}
