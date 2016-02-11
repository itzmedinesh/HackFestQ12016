package com.virtual.price.core;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class Price implements Serializable {

	@JsonProperty
	private String priceRef;

	@JsonProperty
	private String effvDateTime;

	@JsonProperty
	private String changeType;

	@JsonProperty
	private String changeAmount;

	@JsonProperty
	private String regPrice;

	@JsonProperty
	private String state;

	@JsonProperty
	private String createdDate;

	@JsonProperty
	private String createdById;

	@JsonProperty
	private String lastUpdateDate;

	public String getChangeAmount() {
		return changeAmount;
	}

	public String getChangeType() {
		return changeType;
	}

	public String getCreatedById() {
		return createdById;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public String getEffvDateTime() {
		return effvDateTime;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public String getPriceRef() {
		return priceRef;
	}

	public String getRegPrice() {
		return regPrice;
	}

	public String getState() {
		return state;
	}

	public void setChangeAmount(String changeAmount) {
		this.changeAmount = changeAmount;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public void setEffvDateTime(String effvDateTime) {
		this.effvDateTime = effvDateTime;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setPriceRef(String priceRef) {
		this.priceRef = priceRef;
	}

	public void setRegPrice(String regPrice) {
		this.regPrice = regPrice;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Price [priceRef=" + priceRef + ", effvDateTime=" + effvDateTime
				+ ", changeType=" + changeType + ", changeAmount="
				+ changeAmount + ", regPrice=" + regPrice + ", state=" + state
				+ ", createdDate=" + createdDate + ", createdById="
				+ createdById + ", lastUpdateDate=" + lastUpdateDate + "]";
	}

}
