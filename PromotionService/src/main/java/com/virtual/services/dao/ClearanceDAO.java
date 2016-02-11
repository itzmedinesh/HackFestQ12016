package com.virtual.services.dao;

import java.util.Map;
import java.util.Set;

import com.virtual.services.exceptions.PromoDataAccessException;

public interface ClearanceDAO {
	
	public Object retrieveClearanceByTPN(String tpn, String zone)
			throws PromoDataAccessException;
	
	public Map<String,Object> retrieveClearanceByTPNBBulk(Set<String> tpnbList, Set<String> zoneList)
			throws PromoDataAccessException;

}
