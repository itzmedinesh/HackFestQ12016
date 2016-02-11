package com.virtual.services.bo;

import java.util.Map;
import java.util.Set;

import com.virtual.clearance.core.ClearanceProduct;
import com.virtual.services.exceptions.PromoBusinessException;

public interface ClearanceBO {
	
	public ClearanceProduct findClearanceByTPNB(String tpnb, String zone)
			throws PromoBusinessException;

	
	public Map<String,ClearanceProduct> findClearanceByTPNBBulk(Set<String> tpnbList, Set<String> zoneList)
			throws PromoBusinessException;
}
