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
public class Product implements Serializable {

	@JsonProperty
	private String prodType;

	@JsonProperty
	private String prodRef;

	@JsonProperty
	private String locRef;

	@JsonProperty
	private String locType;

	@JsonProperty
	private String lastUpdateDate;

	@JsonProperty
	private Map<String, ProductVariant> tpncToProductVariant = new HashMap<>();

	
	@Override
	public String toString() {
		return "Product [prodType=" + prodType + ", prodRef=" + prodRef
				+ ", locRef=" + locRef + ", locType=" + locType
				+ ", lastUpdateDate=" + lastUpdateDate
				+ ", tpncToProductVariant=" + tpncToProductVariant + "]";
	}

	public Map<String, ProductVariant> getProductVariantByTPNC() {
		return tpncToProductVariant;
	}

	public ProductVariant getProductVariantByTPNC(String tpnc) {
		return tpncToProductVariant.get(tpnc);
	}

	public Map<String, ProductVariant> getTpncToProductVariant() {
		return tpncToProductVariant;
	}

	public String getProdType() {
		return prodType;
	}

	public void setProdType(String prodType) {
		this.prodType = prodType;
	}

	public String getProdRef() {
		return prodRef;
	}

	public void setProdRef(String prodRef) {
		this.prodRef = prodRef;
	}

	public String getLocRef() {
		return locRef;
	}

	public void setLocRef(String locRef) {
		this.locRef = locRef;
	}

	public String getLocType() {
		return locType;
	}

	public void setLocType(String locType) {
		this.locType = locType;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public void setTpncToProductVariant(
			Map<String, ProductVariant> tpncToProductVariant) {
		this.tpncToProductVariant = tpncToProductVariant;
	}

}
