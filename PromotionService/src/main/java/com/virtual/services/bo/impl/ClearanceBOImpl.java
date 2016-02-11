package com.virtual.services.bo.impl;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.clearance.core.ClearanceProduct;
import com.virtual.services.bo.ClearanceBO;
import com.virtual.services.dao.ClearanceDAO;
import com.virtual.services.exceptions.PromoBusinessException;
import com.virtual.services.exceptions.PromoDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class ClearanceBOImpl implements ClearanceBO{
	
	 private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
	            .getLogger(ClearanceBOImpl.class);

	    private ClearanceDAO clearanceDAO;

	    private ObjectMapper objectMapper;

	    public ClearanceBOImpl(ClearanceDAO injectedClearanceDAO,
	                           ObjectMapper injectedObjectMapper) throws PromoDataAccessException {
	        this.clearanceDAO = injectedClearanceDAO;
	        this.objectMapper = injectedObjectMapper;
	    }
	
	 @Override
	    public ClearanceProduct findClearanceByTPNB(String tpnb, String zone)
	            throws PromoBusinessException {
	        ClearanceProduct clearanceProd = null;
	        try {
	            Object clearanceJson = clearanceDAO.retrieveClearanceByTPN(tpnb,
	                    zone);
	            clearanceProd = deserializeJSON(clearanceJson,
	                    ClearanceProduct.class);
	        } catch (PromoDataAccessException e) {
	            throw new PromoBusinessException(e);
	        } catch (IOException e) {
	            throw new PromoBusinessException(e);
	        }
	        return clearanceProd;
	    }
	 
	 @Override
	    public Map<String, ClearanceProduct> findClearanceByTPNBBulk(Set<String> tpnbList, Set<String> zoneList) throws PromoBusinessException {
	        Map<String, ClearanceProduct> clearanceResponseList = new LinkedHashMap<>();
	        Map<String, Object> clearanceDocMap = null;
	        try {
	            try {
	                clearanceDocMap=clearanceDAO.retrieveClearanceByTPNBBulk(tpnbList,zoneList);
	            } catch (PromoDataAccessException e) {
	                e.printStackTrace();
	            }
	            if(!clearanceDocMap.isEmpty()){
	                Iterator<String> clearanceIterator = clearanceDocMap.keySet().iterator();
	                while (clearanceIterator.hasNext()){
	                    String clrKey = clearanceIterator.next();
	                    ClearanceProduct clrEntity = deserializeJSON(clearanceDocMap.get(clrKey), ClearanceProduct.class);
	                    if(clrEntity!=null){
	                       clearanceResponseList.put(clrKey,clrEntity);
	                    }
	                }
	            }
	        } catch (IOException e) {
	            throw new PromoBusinessException(e);
	        }
	        return clearanceResponseList;
	    }

	 
	 @SuppressWarnings("hiding")
	    private <M> M deserializeJSON(Object json, Class<M> clazz)
	            throws IOException {
	        return json != null ? objectMapper.readValue(json.toString(), clazz)
	                : null;
	    }

}
