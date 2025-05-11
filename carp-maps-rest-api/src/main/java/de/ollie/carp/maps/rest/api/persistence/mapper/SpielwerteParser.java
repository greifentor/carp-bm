package de.ollie.carp.maps.rest.api.persistence.mapper;

import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

@Named
class SpielwerteParser {

	public int getRk(String spielwerte) {
		if ((spielwerte == null) || spielwerte.isBlank()) {
			return 10;
		}
		String s = split(spielwerte).getOrDefault("Rüstungsklasse", "10").trim();
		if (s.length() > 2) {
			s = s.substring(0, 2).trim();
		}
		return Integer.valueOf(s);
	}

	private Map<String, String> split(String spielwerte) {
		Map<String, String> m = new HashMap<>();
		tokenize(spielwerte.replace("<b>", "|").replace("&nbsp;", " "))
			.stream()
			.filter(s -> s.contains("</b>"))
			.map(String::strip)
			.forEach(s -> m.put(s.substring(0, s.indexOf("</b>")).strip(), s.substring(s.indexOf("</b>") + 4).strip()));
		return m;
	}

	private List<String> tokenize(String s) {
		StringTokenizer st = new StringTokenizer(s, "|");
		List<String> l = new ArrayList<>();
		while (st.hasMoreTokens()) {
			l.add(st.nextToken().replace(" - ", ""));
		}
		return l;
	}
}
