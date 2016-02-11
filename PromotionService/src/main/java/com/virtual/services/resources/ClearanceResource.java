package com.virtual.services.resources;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;

import com.virtual.clearance.core.ClearanceProduct;
import com.virtual.services.bo.ClearanceBO;
import com.virtual.services.exceptions.PromoBusinessException;
import com.virtual.services.utility.sl4j.LoggerFactoryWrapper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@Path("/clearance")
@Api(value = "/clearance", description = "Promtion CRUD API")
@Produces(MediaType.APPLICATION_JSON)
public class ClearanceResource<T, M> {
	private static final Logger LOGGER = (Logger) LoggerFactoryWrapper
			.getLogger(ClearanceResource.class);

	private ClearanceBO clearanceBO;

	public ClearanceResource(ClearanceBO injectedClearanceBO) {
		clearanceBO = injectedClearanceBO;
	}

	@GET
	@Path("/{tpnb}/{zone}")
	@ApiOperation(value = "Find clearance of a given TPNB and zone")
	@ApiResponses(value = { @ApiResponse(code = 404, message = "Promotion info not found") })
	public Response get(
			@Context UriInfo uriInfo,
			@ApiParam(value = "TPNB of product", required = true) @PathParam("tpnb") String tpnb,
			@ApiParam(value = "Zone of product", required = true) @PathParam("zone") String zone) {
		LOGGER.info("Enter promotion get()");
		ClearanceProduct response = null;
		try {
			response = clearanceBO.findClearanceByTPNB(tpnb, zone);
		} catch (PromoBusinessException e) {
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
	public Response getClearanceList(Set<String> tpnbList, @Context UriInfo uriInfo){
		Map<String,ClearanceProduct> clearanceTpnbList = null;
		String zone = "Z20";
		Set<String> zoneList = new HashSet<String>();
		zoneList.add(zone);
		try{
			clearanceTpnbList = clearanceBO.findClearanceByTPNBBulk(tpnbList, zoneList);

	} catch (PromoBusinessException e) {
			e.printStackTrace();
		}
		return ok(clearanceTpnbList);
	}
	

	private static Response ok(Object entity) {
		return Response.status(HttpServletResponse.SC_OK).entity(entity)
				.build();
	}
}
