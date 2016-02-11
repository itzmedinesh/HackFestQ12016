package com.virtual.services.resources;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.virtual.price.core.Product;
import com.virtual.services.bo.PriceBO;
import com.virtual.services.exceptions.PriceBusinessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/price")
@Api(value = "/price", description = "Promtion CRUD API")
@Produces(MediaType.APPLICATION_JSON)
public class PriceResource<T, M> {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(PriceResource.class);

	private PriceBO<T, M> promotionBO;
	private ObjectMapper objectMapper;

	public PriceResource(PriceBO<T, M> injectedPromotionBO) {
		promotionBO = injectedPromotionBO;
	}

	@GET
	@Path("/{tpnb}/{zone}")
	@ApiOperation(value = "Find promotions of a given TPNB and zone")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Promotion info not found") })
	public Response get(
			@Context UriInfo uriInfo,
			@ApiParam(value = "TPNB of product", required = true) @PathParam("tpnb") String tpnb,
			@ApiParam(value = "Zone of product", required = true) @PathParam("zone") String zone) {
		LOGGER.info("Enter promotion get()");
		List<T> response = null;
		try {
			response = promotionBO.findPriceByTPNB(tpnb, zone);
		} catch (PriceBusinessException e) {
			e.printStackTrace();
		}
		LOGGER.info("Exit promotion get()");
		return ok(response);

	}
	
	
	@POST
	@Path("/list")
	@ApiOperation(
			value = "Find price of products List by product's base 'TPNB' or variants 'TPNC'")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Promotion info not found") })
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getPriceList(
			Set<String> tpnlist,
			@Context UriInfo uriInfo) throws IOException {
		LOGGER.debug("Enter getPriceList()");
			
		Map<String,Product> priceForTpnList = null;
		String zone = "Z1";
		Set<String> zonelist = new HashSet<String>();
		zonelist.add(zone);
		try {
			priceForTpnList = promotionBO.findPriceByTPNBBulk(tpnlist, zonelist);
			System.out.println("after fetch");
		} catch (PriceBusinessException e) {
			e.printStackTrace();
		}

		CacheControl cc = new CacheControl();
		cc.setNoCache(true);
		return ok(priceForTpnList);
	}
	

	@POST
	@Path("/save")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create promotion")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Unable to create promotion") })
	public Response put(@Context UriInfo uriInfo, T promoDoc) {
		LOGGER.debug("Enter promotion put()");
		Object resp = "{}";

		LOGGER.debug("Exit promotion put()");
		return ok(resp);
	}

	private static Response ok(Object entity) {
		return Response.status(HttpServletResponse.SC_OK).entity(entity)
				.build();
	}
}
