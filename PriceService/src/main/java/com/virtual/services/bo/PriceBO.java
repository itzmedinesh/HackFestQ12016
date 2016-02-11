package com.virtual.services.bo;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.virtual.price.core.Product;
import com.virtual.services.exceptions.PriceBusinessException;

public interface PriceBO<T, M> {

	public List<T> findPriceByTPNB(String tpnb, String zone)
			throws PriceBusinessException;
	
	public Map<String,Product>  findPriceByTPNBBulk(Set<String> tpnbList,Set<String> zone)
			throws PriceBusinessException;

}
