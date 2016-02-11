package com.virtual.services.bo;


import java.util.Map;
import java.util.Set;

import com.virtual.promotion.core.OfferEntity;
import com.virtual.promotion.core.PromotionEntity;
import com.virtual.services.exceptions.PromoBusinessException;

public interface PromotionBO {

	public PromotionEntity findPromotionsByTPNB(String tpnb, String zone)
			throws PromoBusinessException;

	public Map<String,PromotionEntity> findPromotionByTPNBBulk(Set<String> tpnbList, Set<String> zoneList)
			throws PromoBusinessException;
	
	public String savePromotion(OfferEntity offerDoc,Set<String> zoneList)
			throws PromoBusinessException;

	

}
