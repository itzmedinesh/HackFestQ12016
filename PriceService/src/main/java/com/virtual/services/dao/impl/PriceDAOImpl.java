package com.virtual.services.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.tesco.couchbase.CouchbaseWrapper;
import com.virtual.services.dao.PriceDAO;
import com.virtual.services.exceptions.PriceDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class PriceDAOImpl<M> implements PriceDAO<M> {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(PriceDAOImpl.class);

	private CouchbaseWrapper couchbaseWrapper;

	public PriceDAOImpl(CouchbaseWrapper injectedCouchWrapper)
			throws PriceDataAccessException {
		this.couchbaseWrapper = injectedCouchWrapper;
	}

	@Override
	public Object retrievePriceByTPN(String tpn, String zone)
			throws PriceDataAccessException {
		Object priceDoc = couchbaseWrapper.get("REGPRICE_" + tpn + "_"
				+ zone);
		return priceDoc;
		
	}
	
	public Map<String, Object> retrievePriceForBulkTPN(Set<String> tpnbList,
			Set<String> zoneList)
			throws PriceDataAccessException {
		List<String> tpnlist = new ArrayList<String>();
		
		for(String tpn : tpnbList){
			for(String zone : zoneList){
				tpnlist.add("REGPRICE_" + tpn + "_"+ zone);
			}
		}
				
		Map<String, Object> docs = couchbaseWrapper.getBulk(tpnlist);
		return docs;
		
	}

}
