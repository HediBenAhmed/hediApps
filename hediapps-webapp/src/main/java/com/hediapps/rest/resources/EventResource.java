package com.hediapps.rest.resources;

import java.util.Date;
import java.util.List;

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

import com.hediapps.backend.model.Event;
import com.hediapps.backend.model.EventSeverity;
import com.hediapps.backend.model.SystemEvent;
import com.hediapps.service.EventService;

@Component
@Path("/events")
public class EventResource {
	private static final Logger logger = LoggerFactory
			.getLogger(EventResource.class);

	@Autowired
	private EventService eventService;

	@Path("dummy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Event getDummyData() {
		SystemEvent event = new SystemEvent();
		event.setId(0);
		event.setEvent("Server restarted");
		event.setSeverity(EventSeverity.INFO);
		event.setEventDate(new Date().getTime());
		return event;
	}

	@Path("/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Event getData(@PathParam("id") long dataId) {
		logger.info("Start getData. ID=" + dataId);

		return eventService.readById(dataId);
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Event> getAllDatas() {
		logger.info("Start getAllDatas.");
		return eventService.readAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Event createData(Event event) {
		logger.info("Start createData.");

		eventService.create(event);
		return event;
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Event updateData(@PathParam("id") long dataId, Event event) {
		logger.info("Start updateNode.");
		eventService.update(event);
		return event;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Event deleteData(@PathParam("id") long dataId) {

		logger.info("Start deleteData.");
		Event event = eventService.readById(dataId);
		eventService.deleteById(dataId);
		return event;
	}

}
