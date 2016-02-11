package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by QZ88 on 19/08/2015.
 */
public class GetListEntity {

	@JsonProperty
	private String getListType;

	@JsonProperty
	private Set<String> items = new HashSet<String>();

	public String getGetListType() {
		return getListType;
	}

	public void setGetListType(String getListType) {
		this.getListType = getListType;
	}

	public Set<String> getItems() {
		return items;
	}

	public void setItems(Set<String> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "GetListEntity{" +
				", buyListType='" + getListType + '\'' +
				", items='" + items +
				'}';
	}


}
