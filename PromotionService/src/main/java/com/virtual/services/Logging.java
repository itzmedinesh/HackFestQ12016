package com.virtual.services;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Logging {

	@NotEmpty
	@JsonProperty
	private String level;

	@NotEmpty
	@JsonProperty
	private FileLogging file = new FileLogging();

	public String getLevel() {
		return level;
	}

	public FileLogging getFile() {
		return file;
	}

	public Logging() {
	}

}
