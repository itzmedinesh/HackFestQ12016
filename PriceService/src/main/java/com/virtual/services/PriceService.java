package com.virtual.services;

import java.lang.reflect.Type;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;
import org.picocontainer.injectors.FactoryInjector;
import org.slf4j.Logger;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;
import com.tesco.couchbase.ConcreteCouchbaseResource;
import com.tesco.couchbase.CouchbaseResource;
import com.virtual.services.bo.PriceBO;
import com.virtual.services.bo.impl.PriceBOImpl;
import com.virtual.services.dao.impl.PriceDAOImpl;
import com.virtual.services.resources.PriceResource;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;
import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.reader.ClassReaders;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class PriceService extends Service<BaseConfiguration> {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(PriceService.class);

	public static void main(String[] args) throws Exception {
		new PriceService().run(args);
	}

	@Override
	public void initialize(Bootstrap<BaseConfiguration> bootstrap) {
		bootstrap.setName("Price Service");
		bootstrap.addBundle(new AssetsBundle("/assets/", "/docs", "index.htm"));
	}

	@Override
	public void run(BaseConfiguration configuration, Environment environment)
			throws Exception {
		LOGGER.info("Configuring dependencies");
		final MutablePicoContainer diContainer = configureDependencies(configuration);
		LOGGER.info("Registering REST resources");
		registerResources(environment, diContainer);
		LOGGER.info("Configuring swagger");
		configureSwagger(environment, configuration);
	}

	/**
	 * @param environment
	 * @param diContainer
	 */
	@SuppressWarnings("rawtypes")
	private void registerResources(Environment environment,
			MutablePicoContainer diContainer) {
		diContainer.addAdapter(priceResourceInjector());
		Set<Class> componentAdapterList = new HashSet<>();
		componentAdapterList.add(PriceResource.class);

		environment.addResource(PriceResource.class);
		environment.addProvider(new PicoProvider(diContainer,
				componentAdapterList));
	}

	/**
	 * @param configuration
	 * @return
	 * @throws Exception
	 */
	private MutablePicoContainer configureDependencies(
			BaseConfiguration configuration) throws Exception {
		MutablePicoContainer diContainer = new DefaultPicoContainer();
		diContainer.addComponent(configuration);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new AfterburnerModule());
		objectMapper.configure(
				DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		diContainer.addComponent(objectMapper);

		CouchbaseResource couchbaseResource = new ConcreteCouchbaseResource(
				configuration.getCouchbaseNodes(),
				configuration.getCouchbaseBucket(),
				configuration.getCouchbaseUsername(),
				configuration.getCouchbasePassword());
		diContainer.addComponent("couchbaseWrapper",
				couchbaseResource.getCouchbaseWrapper());

		diContainer.addComponent(PriceBOImpl.class);
		diContainer.addComponent(PriceDAOImpl.class);
		return diContainer;
	}

	private void configureSwagger(Environment environment,
			BaseConfiguration configuration) throws UnknownHostException,
			SocketException {
		// Swagger Resource
		environment.addResource(new ApiListingResourceJSON());

		// Swagger providers
		environment.addProvider(new ApiDeclarationProvider());
		environment.addProvider(new ResourceListingProvider());

		// Swagger Scanner, which finds all the resources for @Api Annotations
		ScannerFactory.setScanner(new DefaultJaxrsScanner());

		// Add the reader, which scans the resources and extracts the resource
		// information
		ClassReaders.setReader(new DefaultJaxrsApiReader());

		// Set the swagger config options
		SwaggerConfig config = ConfigFactory.config();
		config.setApiVersion("1.0.1");
		config.setBasePath("../");

		environment.addFilter(CrossOriginFilter.class, "/*");
	}

	@SuppressWarnings("rawtypes")
	private FactoryInjector<PriceResource> priceResourceInjector() {
		return new FactoryInjector<PriceResource>() {
			@SuppressWarnings("unchecked")
			@Override
			public PriceResource<?, ?> getComponentInstance(
					PicoContainer picoContainer, Type type) {
				PriceBO<?, ?> promotionBO = picoContainer
						.getComponent(PriceBOImpl.class);
				return new PriceResource(promotionBO);
			}
		};
	}
}
