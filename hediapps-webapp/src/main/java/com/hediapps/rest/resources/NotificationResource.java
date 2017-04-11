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

import com.hediapps.backend.model.EventSeverity;
import com.hediapps.backend.model.Notification;
import com.hediapps.backend.model.SystemEvent;
import com.hediapps.service.NotificationService;

@Component
@Path("/notifications")
public class NotificationResource {
	private static final Logger logger = LoggerFactory
			.getLogger(NotificationResource.class);

	@Autowired
	private NotificationService notificationService;

	@Path("dummy")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Notification getDummyData() {
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
	public Notification getData(@PathParam("id") long dataId) {
		logger.info("Start getData. ID=" + dataId);

		return notificationService.readById(dataId);
	}

	@Path("/")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Notification> getAllDatas() {
		logger.info("Start getAllDatas.");
		return notificationService.readAll();
	}

	@Path("/")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Notification createData(Notification notification) {
		logger.info("Start createData.");

		notificationService.create(notification);
		return notification;
	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Notification updateData(@PathParam("id") long dataId,
			Notification notification) {
		logger.info("Start updateNode.");
		notificationService.update(notification);
		return notification;
	}

	@Path("/{id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Notification deleteData(@PathParam("id") long dataId) {

		logger.info("Start deleteData.");
		Notification notification = notificationService.readById(dataId);
		notificationService.deleteById(dataId);
		return notification;
	}

}
