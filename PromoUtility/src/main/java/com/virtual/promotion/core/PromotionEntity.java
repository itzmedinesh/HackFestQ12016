package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

/**
 * Created by QZ88 on 20/08/2015.
 */
public class PromotionEntity {

	public Map<String, OfferEntity> getOfferEntities() {
		return offerEntities;
	}

	public void setOfferEntities(Map<String, OfferEntity> offerEntities) {
		this.offerEntities = offerEntities;
	}

	@JsonProperty("offers")
	public Map<String,OfferEntity> offerEntities;



	public String toString() {
		return "PromotionEntity{" +
				"offerEntities='" + offerEntities +
				'}';
	}


}
