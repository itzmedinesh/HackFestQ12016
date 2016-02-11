package com.virtual.clearance.core;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class ClearancePrice implements Serializable {

	@JsonProperty
	private String clearanceId;

	@JsonProperty
	private String effectiveDate;

	@JsonProperty
	private String endDate;

	@JsonProperty
	private String clrPrice;

	public String getClearanceId() {
		return clearanceId;
	}

	public String getClrPrice() {
		return clrPrice;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setClearanceId(String clearanceId) {
		this.clearanceId = clearanceId;
	}

	public void setClrPrice(String clrPrice) {
		this.clrPrice = clrPrice;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "ClearancePrice [clearanceId=" + clearanceId
				+ ", effectiveDate=" + effectiveDate + ", endDate=" + endDate
				+ ", clrPrice=" + clrPrice + "]";
	}

}
