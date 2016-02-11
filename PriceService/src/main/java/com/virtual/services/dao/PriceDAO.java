package com.virtual.services.dao;

import java.util.Map;
import java.util.Set;

import com.virtual.services.exceptions.PriceDataAccessException;

public interface PriceDAO<M> {

	public Object retrievePriceByTPN(String tpn, String zone)
			throws PriceDataAccessException;
	
	public Map<String, Object> retrievePriceForBulkTPN(Set<String> tpnlist,
			Set<String> zone) throws PriceDataAccessException;

}