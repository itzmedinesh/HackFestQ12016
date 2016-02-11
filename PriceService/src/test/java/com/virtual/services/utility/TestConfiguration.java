package com.virtual.services.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import com.virtual.services.BaseConfiguration;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class TestConfiguration extends BaseConfiguration {

	public static Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(TestConfiguration.class);

	private Map<String, Object> configuration = null;

	@SuppressWarnings("unchecked")
	public TestConfiguration() {
		Yaml yamlConfiguration = new Yaml();
		try {
			String filename = "test.yml";
			configuration = (Map<String, Object>) yamlConfiguration
					.load(new String(Files.readAllBytes(Paths.get(filename))));
		} catch (IOException exception) {
			LOGGER.error(exception.getMessage());
		}
	}

	public TestConfiguration withBucketName(String bucketName) {
		this.setCouchbaseBucket(bucketName);
		return this;
	}

	public static TestConfiguration load() {

		ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
		try {
			String config = null;
			String environment = System.getProperty("environment");
			if (StringUtils.isEmpty(environment)) {
				config = "test.yml";
			}
			return objectMapper.readValue(new File(config),
					TestConfiguration.class);
		} catch (IOException e) {
			LOGGER.info("Error in TestConfiguration", e.getMessage());
			return null;
		}
	}

	public String getCouchbaseAdminUsername() {
		return (String) configuration.get("couchbase.admin.username");
	}

	public String getCouchbaseAdminPassword() {
		return (String) configuration.get("couchbase.admin.password");
	}

	public String getCouchbaseBucket() {
		return (String) configuration.get("couchbase.bucket");
	}

	public String getCouchbasePassword() {
		return (String) configuration.get("couchbase.password");
	}

}
