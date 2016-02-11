package com.virtual.services;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FileLogging {

	@NotEmpty
	@JsonProperty
	private String enabled;

	@NotEmpty
	@JsonProperty
	private String threshold;

	@NotEmpty
	@JsonProperty
	private String currentLogFilename;

	@NotEmpty
	@JsonProperty
	private String archivedLogFilenamePattern;

	public String getEnabled() {
		return enabled;
	}

	public String getThreshold() {
		return threshold;
	}

	public String getCurrentLogFilename() {
		return currentLogFilename;
	}

	public String getArchivedLogFilenamePattern() {
		return archivedLogFilenamePattern;
	}

	public FileLogging() {
	}

}
