package com.virtual.services.bo.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.price.core.Product;
import com.virtual.services.bo.PriceBO;
import com.virtual.services.dao.PriceDAO;
import com.virtual.services.exceptions.PriceBusinessException;
import com.virtual.services.exceptions.PriceDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class PriceBOImpl<T, M> implements PriceBO<T, M> {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(PriceBOImpl.class);

	private PriceDAO<M> priceDAO;
	private ObjectMapper objectMapper;

	public PriceBOImpl(PriceDAO<M> injectedPriceDAO,ObjectMapper injectedObjectMapper)
			throws PriceDataAccessException {
		this.priceDAO = injectedPriceDAO;
		this.objectMapper = injectedObjectMapper;
	}

	@Override
	public List<T> findPriceByTPNB(String tpnb, String zone)
			throws PriceBusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Map<String,Product> findPriceByTPNBBulk(Set<String> tpnbList, Set<String> zoneList)
			throws PriceBusinessException {
		//Map<String, List<Product>> finalResponse = new LinkedHashMap<>();
		Map<String,Product> priceResponseList = new LinkedHashMap<>();
		Product priceResponse = null;
		Map<String, Object> priceDocMap = null;
			try {
					priceDocMap = priceDAO.retrievePriceForBulkTPN(tpnbList,zoneList);
				if(!priceDocMap.isEmpty()){
					Iterator<String> priceIterator = priceDocMap.keySet().iterator();
					while (priceIterator.hasNext()) {
						String priceKey = priceIterator.next();
						System.out.println("key:"+priceKey);
						System.out.println("value:"+priceDocMap.get(priceKey));
						Product priceEntity = deserializeJSON(
								priceDocMap.get(priceKey),com.virtual.price.core.Product.class);
						if(priceEntity!=null){
							priceResponseList.put(priceKey,priceEntity);
						}
					}
				}
			}
			catch (Exception e) {
				throw new PriceBusinessException(e);
			}

		return priceResponseList;
	}
	
	@SuppressWarnings("hiding")
	private <M> M deserializeJSON(Object json, Class<M> clazz)
			throws IOException {
		return json != null ? objectMapper.readValue(json.toString(), clazz)
				: null;
	}

}
