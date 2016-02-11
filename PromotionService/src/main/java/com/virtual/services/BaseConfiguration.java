package com.virtual.services;

import com.fasterxml.jackson.annotation.JsonProperty;

/*Configuration class*/

public class BaseConfiguration extends
		com.yammer.dropwizard.config.Configuration {

	@JsonProperty
	private Logging logging = new Logging();

	@JsonProperty("couchbase.nodes")
	private String[] couchbaseNodes;

	@JsonProperty("couchbase.bucket")
	private String couchbaseBucket;

	@JsonProperty("couchbase.admin.username")
	private String couchbaseAdminUsername;

	@JsonProperty("couchbase.admin.password")
	private String couchbaseAdminPassword;

	@JsonProperty("couchbase.username")
	private String couchbaseUsername;

	@JsonProperty("couchbase.password")
	private String couchbasePassword;

	@JsonProperty("dummyCouchbaseMode")
	private boolean dummyCouchbaseMode;

	public BaseConfiguration() {
	}

	public String getCouchbaseAdminPassword() {
		return couchbaseAdminPassword;
	}

	public String getCouchbaseAdminUsername() {
		return couchbaseAdminUsername;
	}

	public String getCouchbaseBucket() {
		return couchbaseBucket;
	}

	public String[] getCouchbaseNodes() {
		return couchbaseNodes;
	}

	public String getCouchbasePassword() {
		return couchbasePassword;
	}

	public String getCouchbaseUsername() {
		return couchbaseUsername;
	}

	public Logging getLogging() {
		return logging;
	}

	public boolean isDummyCouchbaseMode() {
		return dummyCouchbaseMode;
	}

	protected void setCouchbaseBucket(String couchbaseBucket) {
		this.couchbaseBucket = couchbaseBucket;
	}

	public void setCouchbaseNodes(String[] couchbaseNodes) {
		String couchNodes[] = new String[0];
		if (couchbaseNodes != null) {
			couchNodes = couchbaseNodes.clone();
		}
		this.couchbaseNodes = couchNodes;
	}

}
