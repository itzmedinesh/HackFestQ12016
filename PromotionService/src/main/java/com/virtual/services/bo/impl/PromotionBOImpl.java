package com.virtual.services.bo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.promotion.core.BuyListEntity;
import com.virtual.promotion.core.OfferEntity;
import com.virtual.promotion.core.PromotionEntity;
import com.virtual.services.bo.PromotionBO;
import com.virtual.services.dao.PromotionDAO;
import com.virtual.services.exceptions.PromoBusinessException;
import com.virtual.services.exceptions.PromoDataAccessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;

public class PromotionBOImpl implements PromotionBO {
    private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
            .getLogger(PromotionBOImpl.class);

    private PromotionDAO promotionDAO;

    private ObjectMapper objectMapper;

    public PromotionBOImpl(PromotionDAO injectedPromotionDAO,
                           ObjectMapper injectedObjectMapper) throws PromoDataAccessException {
        this.promotionDAO = injectedPromotionDAO;
        this.objectMapper = injectedObjectMapper;
    }

    @Override
    public PromotionEntity findPromotionsByTPNB(String tpnb, String zone)
            throws PromoBusinessException {
        PromotionEntity promos = null;
        LOGGER.info("Enter findPromotionsByTPNB");
        try {
            Object promoJson = promotionDAO.retrievePromotionByTPN(tpnb, zone);
            promos = deserializeJSON(promoJson, PromotionEntity.class);
        } catch (PromoDataAccessException e) {
            throw new PromoBusinessException(e);
        } catch (IOException e) {
            throw new PromoBusinessException(e);
        }
        LOGGER.info("Exit findPromotionsByTPNB");
        return promos;
    }

    @Override
    public Map<String, PromotionEntity> findPromotionByTPNBBulk(Set<String> tpnbList, Set<String> zoneList) throws PromoBusinessException {
        Map<String, PromotionEntity> promoResponseList = new LinkedHashMap<>();
        Map<String, Object> promoDocMap = null;
        try {
            try {
                promoDocMap=promotionDAO.retrievePromotionByTPNBBulk(tpnbList,zoneList);
            } catch (PromoDataAccessException e) {
                e.printStackTrace();
            }
            if(!promoDocMap.isEmpty()){
                Iterator<String> promoIterator = promoDocMap.keySet().iterator();
                while (promoIterator.hasNext()){
                    String promoKey = promoIterator.next();
                    PromotionEntity promoEntity = deserializeJSON(promoDocMap.get(promoKey), PromotionEntity.class);
                    if(promoEntity!=null){
                    	promoResponseList.put(promoKey,promoEntity);
                    }
                }
            }
        } catch (IOException e) {
            throw new PromoBusinessException(e);
        }
        return promoResponseList;
    }

    
    @Override
    public String savePromotion(OfferEntity offerDoc, Set<String> zoneList)
            throws PromoBusinessException {
    try {
    	PromotionEntity promoItemZoneDoc = new PromotionEntity();
    	Map<String,OfferEntity> offerEntities = new HashMap<String,OfferEntity>();
    	offerEntities.put(offerDoc.getOfferid(), offerDoc);
    	promoItemZoneDoc.setOfferEntities(offerEntities);
    
    	
    	List<BuyListEntity> buyLists=offerDoc.getBuyListEntities();
    	List<String> tpnbList = new ArrayList<String>();
    	for (BuyListEntity buyList : buyLists) {
    	if(buyList!=null){
    		Set<String> itemSet = new HashSet<String>();
    			itemSet = buyList.getItems();
    			if(!itemSet.isEmpty()){
    				   Iterator<String> itemIterator = itemSet.iterator();
    				   while(itemIterator.hasNext()){
    					   tpnbList.add(itemIterator.next());  
    				}
    			}
    		}
    	}
    	  
    	String promoItemZoneString = objectMapper.writeValueAsString(promoItemZoneDoc);
    	
    	for (String tpnb : tpnbList) {
			for (String zone : zoneList) {
				String promoItemZoneKey = "PROMO_" + tpnb + "_" + zone;
			 	promotionDAO.createPromotionDocument(promoItemZoneKey, promoItemZoneString);
			}
    	}
   
    }
    catch (Exception e) {
        throw new PromoBusinessException(e);
    }
    	
        return offerDoc.getOfferid();
    }

   
   

    @SuppressWarnings("hiding")
    private <M> M deserializeJSON(Object json, Class<M> clazz)
            throws IOException {
        return json != null ? objectMapper.readValue(json.toString(), clazz)
                : null;
    }
}
