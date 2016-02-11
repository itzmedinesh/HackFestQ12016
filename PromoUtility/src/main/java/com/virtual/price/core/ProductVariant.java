package com.virtual.price.core;

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
public class ProductVariant implements Serializable {
	@Override
	public String toString() {
		return "ProductVariant [tpnc=" + tpnc + ", sellingUOM=" + sellingUOM
				+ ", selling_currency=" + selling_currency
				+ ", effective_date=" + effective_date + "]";
	}

	@JsonProperty
	private String tpnc;

	@JsonProperty
	private String sellingUOM;

	@JsonProperty
	private String selling_currency;
	
	@JsonProperty
	private Map<String, Price> effective_date = new HashMap<>();

//	public ProductVariant(String tpnc) {
//		this.tpnc = tpnc;
//	}

	
	public String getTPNC() {
		return tpnc;
	}

	public String getSellingUOM() {
		return sellingUOM;
	}

	public void setSellingUOM(String sellingUOM) {
		this.sellingUOM = sellingUOM;
	}

	public String getTpnc() {
		return tpnc;
	}

	public void setTpnc(String tpnc) {
		this.tpnc = tpnc;
	}

	public String getSelling_currency() {
		return selling_currency;
	}

	public void setSelling_currency(String selling_currency) {
		this.selling_currency = selling_currency;
	}

	public Map<String, Price> getEffective_date() {
		return effective_date;
	}

	public void setEffective_date(Map<String, Price> effective_date) {
		this.effective_date = effective_date;
	}
	
	
	
}
