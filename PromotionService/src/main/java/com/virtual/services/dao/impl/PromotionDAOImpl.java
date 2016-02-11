package com.virtual.services.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.tesco.couchbase.CouchbaseWrapper;
import com.virtual.services.dao.PromotionDAO;
import com.virtual.services.exceptions.PromoDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class PromotionDAOImpl implements PromotionDAO {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(PromotionDAOImpl.class);

	private CouchbaseWrapper couchbaseWrapper;

	public PromotionDAOImpl(CouchbaseWrapper injectedCouchWrapper)
			throws PromoDataAccessException {
		this.couchbaseWrapper = injectedCouchWrapper;
	}

	@Override
	public Object retrievePromotionByTPN(String tpn, String zone)
			throws PromoDataAccessException {
		LOGGER.info("Enter retrievePromotionByTPN");
		Object promoDoc = couchbaseWrapper.get("PROMO_" + tpn + "_" + zone);
		LOGGER.info("Exit retrievePromotionByTPN");
		return promoDoc;
	}
	
	@Override
	public Map<String, Object> retrievePromotionByTPNBBulk(Set<String> tpnbList, Set<String> zoneList) throws PromoDataAccessException {
		List<String> tpnList = new ArrayList<String>();
		for (String tpnb : tpnbList) {
			for (String zone : zoneList) {
				tpnList.add("PROMO_" + tpnb + "_" + zone);
			}
		}
		Map<String, Object> docs = couchbaseWrapper.getBulk(tpnList);
		return docs;
	}


	@Override
	public boolean createPromotionDocument(String promoItemZoneKey,
			String promoItemZoneString) throws PromoDataAccessException {
		couchbaseWrapper.set(promoItemZoneKey, promoItemZoneString);
		return true;
	}

	
}
