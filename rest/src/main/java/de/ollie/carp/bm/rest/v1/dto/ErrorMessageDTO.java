package de.ollie.carp.bm.rest.v1.dto;

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

	public String[] getMessageDataToStringArray() {
		List<String> l = new ArrayList<>();
		messageData
			.entrySet()
			.stream()
			.forEach(e -> {
				l.add(e.getKey());
				l.add(e.getValue());
			});
		return l.toArray(new String[l.size()]);
	}
}