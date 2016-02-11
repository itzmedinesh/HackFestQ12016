package com.virtual.clearance.core;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class ClearanceProduct implements Serializable {

	@JsonProperty
	private String prodType;

	@JsonProperty
	private String prodRef;

	@JsonProperty
	private String locRef;

	@JsonProperty
	private String locType;

	@JsonProperty
	private String createdDate;

	@JsonProperty
	private String createdById;

	@JsonProperty
	private String lastUpdateDate;

	@JsonProperty
	private String lastUpdatedById;

	@JsonProperty
	private Map<String, ClearancePrice> effective_date = new HashMap<>();

	public String getCreatedById() {
		return createdById;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public Map<String, ClearancePrice> getEffective_date() {
		return effective_date;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public String getLastUpdatedById() {
		return lastUpdatedById;
	}

	public String getLocRef() {
		return locRef;
	}

	public String getLocType() {
		return locType;
	}

	public String getProdRef() {
		return prodRef;
	}

	public String getProdType() {
		return prodType;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setEffective_date(Map<String, ClearancePrice> effective_date) {
		this.effective_date = effective_date;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setLastUpdatedById(String lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}

	public void setLocRef(String locRef) {
		this.locRef = locRef;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public void setProdRef(String prodRef) {
		this.prodRef = prodRef;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	@Override
	public String toString() {
		return "ClearanceProduct [prodType=" + prodType + ", prodRef="
				+ prodRef + ", locRef=" + locRef + ", locType=" + locType
				+ ", createdDate=" + createdDate + ", createdById="
				+ createdById + ", lastUpdateDate=" + lastUpdateDate
				+ ", lastUpdatedById=" + lastUpdatedById + ", effective_date="
				+ effective_date + "]";
	}

}
