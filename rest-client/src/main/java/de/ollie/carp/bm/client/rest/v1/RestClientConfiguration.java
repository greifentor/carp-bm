package de.ollie.carp.bm.client.rest.v1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestClientConfiguration {

	static final String DEFAULT_HOST = "localhost";
	static final String DEFAULT_PORT = "8080";
	static final String DEFAULT_SCHEMA = "http";

	@Value("${rest.server.host:" + DEFAULT_HOST + "}")
	private String host;

	@Value("${rest.server.port:" + DEFAULT_PORT + "}")
	private Integer port;

	@Value("${rest.server.schema:" + DEFAULT_SCHEMA + "}")
	private String schema;

	public String getServerSchemaHostAndPort() {
		return getServerSchemaHost() + getPortSuffix();
	}

	private String getServerSchemaHost() {
		return schema + "://" + host;
	}

	private String getPortSuffix() {
		return port != null ? ":" + port : "";
	}
}
