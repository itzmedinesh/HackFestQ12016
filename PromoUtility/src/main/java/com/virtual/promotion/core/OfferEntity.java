package com.virtual.promotion.core;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by QZ88 on 19/08/2015.
 */
public class OfferEntity {

	@JsonProperty
	private String offerid;

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

	@JsonProperty
	private String locRef;

	@JsonProperty
	private String locType;

	@JsonProperty
	private String multi_buy_promo_type;

	@JsonProperty
	private String name;

	@JsonProperty
	private String externalPromoId;

	@JsonProperty
	private String tillRollDescription;

	@JsonProperty
	private String couponTriggeredInd;

	@JsonProperty
	private String couponNumber;

	@JsonProperty
	private String couponDescription;

	@JsonProperty
	private String createdDate;

	@JsonProperty
	private String createdById;

	@JsonProperty
	private String lastUpdateDate;

	@JsonProperty
	private String lastUpdatedById;

	@JsonProperty
	private String wasPrice;

	@JsonProperty
	private String wasWasPrice;

	@JsonProperty
	private String posLabelReqInd;

	@JsonProperty
	private String effectiveDate;

	@JsonProperty
	private String endDate;

	@JsonProperty
	private String rpmPromoCompDetailId;

	@JsonProperty("promoBuyList")
	public List<BuyListEntity> buyListEntities;

	@JsonProperty("promoGetList")
	public List<GetListEntity> getListEntities;

	@JsonProperty("promoThresholds")
	public List<PromoThresholdEntity> promoThresholdEntities;

	@JsonProperty("promoRewards")
	public List<PromoRewardEntity> promoRewardEntities;


	public List<PromoRewardEntity> getPromoRewardEntities() {
		return promoRewardEntities;
	}

	public void setPromoRewardEntities(
			List<PromoRewardEntity> promoRewardEntities) {
		this.promoRewardEntities = promoRewardEntities;
	}

	public List<PromoThresholdEntity> getPromoThresholdEntities() {
		return promoThresholdEntities;
	}

	public void setPromoThresholdEntities(
			List<PromoThresholdEntity> promoThresholdEntities) {
		this.promoThresholdEntities = promoThresholdEntities;
	}

	public String getRpmPromoCompDetailId() {
		return rpmPromoCompDetailId;
	}

	public void setRpmPromoCompDetailId(String rpmPromoCompDetailId) {
		this.rpmPromoCompDetailId = rpmPromoCompDetailId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getPosLabelReqInd() {
		return posLabelReqInd;
	}

	public void setPosLabelReqInd(String posLabelReqInd) {
		this.posLabelReqInd = posLabelReqInd;
	}

	public String getWasWasPrice() {
		return wasWasPrice;
	}

	public void setWasWasPrice(String wasWasPrice) {
		this.wasWasPrice = wasWasPrice;
	}

	public String getWasPrice() {
		return wasPrice;
	}

	public void setWasPrice(String wasPrice) {
		this.wasPrice = wasPrice;
	}

	public String getLastUpdatedById() {
		return lastUpdatedById;
	}

	public void setLastUpdatedById(String lastUpdatedById) {
		this.lastUpdatedById = lastUpdatedById;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getCreatedById() {
		return createdById;
	}

	public void setCreatedById(String createdById) {
		this.createdById = createdById;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCouponDescription() {
		return couponDescription;
	}

	public void setCouponDescription(String couponDescription) {
		this.couponDescription = couponDescription;
	}

	public String getCouponNumber() {
		return couponNumber;
	}

	public void setCouponNumber(String couponNumber) {
		this.couponNumber = couponNumber;
	}

	public String getCouponTriggeredInd() {
		return couponTriggeredInd;
	}

	public void setCouponTriggeredInd(String couponTriggeredInd) {
		this.couponTriggeredInd = couponTriggeredInd;
	}

	public String getTillRollDescription() {
		return tillRollDescription;
	}

	public void setTillRollDescription(String tillRollDescription) {
		this.tillRollDescription = tillRollDescription;
	}

	public String getExternalPromoId() {
		return externalPromoId;
	}

	public void setExternalPromoId(String externalPromoId) {
		this.externalPromoId = externalPromoId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMulti_buy_promo_type() {
		return multi_buy_promo_type;
	}

	public void setMulti_buy_promo_type(String multi_buy_promo_type) {
		this.multi_buy_promo_type = multi_buy_promo_type;
	}

	public String getOfferid() {
		return offerid;
	}

	public void setOfferid(String offerid) {
		this.offerid = offerid;
	}

	public List<BuyListEntity> getBuyListEntities() {
		return buyListEntities;
	}

	public void setBuyListEntities(List<BuyListEntity> buyListEntities) {
		this.buyListEntities = buyListEntities;
	}

	public List<GetListEntity> getGetListEntities() {
		return getListEntities;
	}

	public void setGetListEntities(List<GetListEntity> getListEntities) {
		this.getListEntities = getListEntities;
	}

	public void addPromoThresholdEntity(PromoThresholdEntity promoThresholdEntity) {
		if(this.promoThresholdEntities == null){
			this.promoThresholdEntities = new ArrayList<>();
		}
		this.promoThresholdEntities.add(promoThresholdEntity);
	}

	public void addPromoRewardEntities(PromoRewardEntity promoRewardEntities) {
		if(this.promoRewardEntities == null){
			this.promoRewardEntities = new ArrayList<>();
		}
		this.promoRewardEntities.add(promoRewardEntities);
	}


	public void addBuyListEntities(BuyListEntity buyListEntities) {
		if(this.buyListEntities == null){
			this.buyListEntities = new ArrayList<>();
		}
		this.buyListEntities.add(buyListEntities);
	}
	public void addGuyListEntities(GetListEntity getListEntities) {
		if(this.getListEntities == null){
			this.getListEntities = new ArrayList<>();
		}
		this.getListEntities.add(getListEntities);
	}


	@Override
	public String toString() {
		return "OfferEntity{" +
				", offerid='" + offerid + '\'' +
				", locRef='" + locRef + '\'' +
				", locType='" + locType + '\'' +
				", multi_buy_promo_type='" + multi_buy_promo_type + '\'' +
				", name='" + name   + '\'' +
				", externalPromoId='" + externalPromoId + '\'' +
				", tillRollDescription='" + tillRollDescription + '\'' +
				", couponTriggeredInd='" + couponTriggeredInd + '\'' +
				", couponNumber='" + couponNumber + '\'' +
				", couponDescription='" + couponDescription + '\'' +
				", createdDate='" + createdDate + '\'' +
				", createdById=" + createdById +
				", lastUpdateDate='" + lastUpdateDate + '\'' +
				", lastUpdatedById='" + lastUpdatedById + '\'' +
				", wasPrice='" + wasPrice + '\'' +
				", wasWasPrice='" + wasWasPrice + '\'' +
				", posLabelReqInd='" + posLabelReqInd + '\'' +
				", effectiveDate='" + effectiveDate + '\'' +
				", endDate=" + endDate +
				", rpmPromoCompDetailId=" + rpmPromoCompDetailId +
				", promoThresholdEntities=" + promoThresholdEntities +
				", promoRewardEntities=" + promoRewardEntities +
				", buyListEntities=" + buyListEntities +
				", getListEntities=" + getListEntities +
				'}';
	}

}
