package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by QZ88 on 19/08/2015.
 */
public class BuyListEntity {

	@JsonProperty
	private String buyListType;

	@JsonProperty
	private Set<String> items = new HashSet<String>();

	public Set<String> getItems() {
		return items;
	}

	public void setItems(Set<String> items) {
		this.items = items;
	}

	public String getBuyListType() {
		return buyListType;
	}

	public void setBuyListType(String buyListType) {
		this.buyListType = buyListType;
	}

	@Override
	public String toString() {
		return "BuyListEntity{" +
				", buyListType='" + buyListType + '\'' +
				", items='" + items +
				'}';
	}

}
