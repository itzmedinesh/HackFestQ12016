package com.tesco.services.utility;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesco.couchbase.CouchbaseWrapper;

/**
 * Created by QZ88 on 19/08/2015.
 */
public class Helper {


	public Object getGenericObject(CouchbaseWrapper couchbaseWrapper,ObjectMapper mapper,String key, final Class clazz) {
		Object o = null;
		String objectJson = (String) couchbaseWrapper.get(key);

		if (objectJson != null) {

			try {
				o = mapper.readValue(objectJson, clazz);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return o;
	}
}
