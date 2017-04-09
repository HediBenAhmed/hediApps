package com.hediapps.rest.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hediapps.backend.model.Data;
import com.hediapps.service.DataService;

@Component
@Path("/datas")
public class DataResource {
	private static final Logger logger = LoggerFactory.getLogger(DataResource.class);

	@Autowired
	private DataService dataService;

	@Path("dummy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Data getDummyData() {
		logger.info("Start getDummyData");
		Data data = new Data();
		data.setId(0);
		data.setName("data");
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("a", 0.5d);
		data.setValues(values);
		return data;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Data getData(@PathParam("id") long dataId) {
		logger.info("Start getData. ID=" + dataId);

		return dataService.readById(dataId);
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Data> getAllDatas() {
		logger.info("Start getAllDatas.");
		return dataService.readAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Data createData(Data data) {
		logger.info("Start createData.");

		dataService.create(data);
		return data;
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Data updateData(@PathParam("id") long dataId, Data data) {
		logger.info("Start updateNode.");
		dataService.update(data);
		return data;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Data deleteData(@PathParam("id") long dataId) {

		logger.info("Start deleteData.");
		Data data = dataService.readById(dataId);
		dataService.deleteById(dataId);
		return data;
	}

}
