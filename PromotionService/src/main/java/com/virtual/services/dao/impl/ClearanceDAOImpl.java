package com.virtual.services.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.tesco.couchbase.CouchbaseWrapper;
import com.virtual.services.dao.ClearanceDAO;
import com.virtual.services.exceptions.PromoDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class ClearanceDAOImpl implements ClearanceDAO {
	
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(ClearanceDAOImpl.class);

	private CouchbaseWrapper couchbaseWrapper;

	public ClearanceDAOImpl(CouchbaseWrapper injectedCouchWrapper)
			throws PromoDataAccessException {
		this.couchbaseWrapper = injectedCouchWrapper;
	}
	
	@Override
	public Map<String, Object> retrieveClearanceByTPNBBulk(Set<String> tpnbList, Set<String> zoneList) throws PromoDataAccessException {
		List<String> tpnList = new ArrayList<String>();
		for (String tpnb : tpnbList) {
			for (String zone : zoneList) {
				tpnList.add("PRODUCTCLR_" + tpnb + "_" + zone);
			}
		}
		Map<String, Object> docs = couchbaseWrapper.getBulk(tpnList);
		return docs;
	}


	@Override
	public Object retrieveClearanceByTPN(String tpn, String zone)
			throws PromoDataAccessException {
		LOGGER.info("Enter retrieveClearanceByTPN");
		Object clearanceDoc = couchbaseWrapper.get("PRODUCTCLR_" + tpn + "_"
				+ zone);
		LOGGER.info("Exit retrieveClearanceByTPN");
		return clearanceDoc;
	}

}
