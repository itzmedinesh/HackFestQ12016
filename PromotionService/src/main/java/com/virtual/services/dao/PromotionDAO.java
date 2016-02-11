package com.virtual.services.dao;

import java.util.Map;
import java.util.Set;

import com.virtual.services.exceptions.PromoDataAccessException;

public interface PromotionDAO {

	public Object retrievePromotionByTPN(String tpn, String zone)
			throws PromoDataAccessException;
	
	public Map<String,Object> retrievePromotionByTPNBBulk(Set<String> tpnbList, Set<String> zoneList)
			throws PromoDataAccessException;

	public boolean createPromotionDocument(String promoItemZoneKey,
			String promoItemZoneString) throws PromoDataAccessException;


}