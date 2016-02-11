package com.tesco.services.utility;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tesco.couchbase.CouchbaseWrapper;
import com.tesco.couchbase.testutils.AsyncCouchbaseWrapperStub;
import com.tesco.couchbase.testutils.BucketTool;
import com.tesco.couchbase.testutils.CouchbaseTestManager;
import com.tesco.couchbase.testutils.CouchbaseWrapperStub;
import com.virtual.promotion.core.PromotionEntity;
import com.virtual.services.bo.impl.PromotionBOImpl;

/**
 * Created by Nibedita on 06/08/2014.
 */
@RunWith(MockitoJUnitRunner.class)
public class DockyardTest {
	TestConfiguration testConfiguration;
	CouchbaseTestManager couchbaseTestManager;
	CouchbaseWrapper couchbaseWrapper;
	ObjectMapper mapper;
	Helper helper = new Helper();
	PromotionBOImpl promotionBO;


	@Before
	public void setUp() throws IOException,URISyntaxException,InterruptedException {

		 testConfiguration = TestConfiguration.load();

		if (testConfiguration.isDummyCouchbaseMode()){
			Map<String, ImmutablePair<Long, String>> fakeBase = new HashMap<>();
			couchbaseTestManager = new CouchbaseTestManager(new CouchbaseWrapperStub(fakeBase),
					new AsyncCouchbaseWrapperStub(fakeBase),
					mock(BucketTool.class));
		} else {
			couchbaseTestManager = new CouchbaseTestManager(testConfiguration.getCouchbaseBucket(),
					testConfiguration.getCouchbaseUsername(),
					testConfiguration.getCouchbasePassword(),
					testConfiguration.getCouchbaseNodes(),
					testConfiguration.getCouchbaseAdminUsername(),
					testConfiguration.getCouchbaseAdminPassword());
		}

		couchbaseWrapper = couchbaseTestManager.getCouchbaseWrapper();

		mapper = new ObjectMapper();

	}


	@Test
	public void testDBObject(){
		String key="PROMO_01234567_Z5";

		Object jsonDoc = (Object)couchbaseWrapper.get(key);
		PromotionEntity promotionEntity = (PromotionEntity)helper.getGenericObject(couchbaseWrapper,mapper,key,PromotionEntity.class);
		System.out.println(jsonDoc);
		System.out.println(promotionEntity);
		Assert.assertEquals(jsonDoc.toString(),promotionEntity.toString());

	}
	@Test
	public void testRetivePromotionForItem(){
		try {
			PromotionEntity promotionEntity = (PromotionEntity)promotionBO
					.findPromotionsByTPNB("01234567", "5");
			System.out.println(promotionEntity);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	}
