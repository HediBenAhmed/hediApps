package com.hediapps.rest.resources;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.backend.model.Event;
import com.hediapps.backend.model.EventSeverity;
import com.hediapps.backend.model.SystemEvent;
import com.hediapps.service.EventService;

@Component
@RequestMapping("/rest/events")
public class EventResource {
	private static final Logger logger = LoggerFactory.getLogger(EventResource.class);

	@Autowired
	private EventService eventService;

	@RequestMapping(value = "dummy", method = RequestMethod.GET)
	@ResponseBody
	public Event getDummyData() {
		SystemEvent event = new SystemEvent();
		event.setId(0);
		event.setEvent("Server restarted");
		event.setSeverity(EventSeverity.INFO);
		event.setEventDate(new Date().getTime());
		return event;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Event getData(@PathVariable long id) {
		logger.info("Start getData. ID=" + id);

		return eventService.readById(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllDatas() {
		logger.info("Start getAllDatas.");
		return eventService.readAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Event createData(Event event) {
		logger.info("Start createData.");

		eventService.create(event);
		return event;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Event updateData(Event event) {
		logger.info("Start updateNode.");
		eventService.update(event);
		return event;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Event deleteData(@PathVariable long id) {

		logger.info("Start deleteData.");
		Event event = eventService.readById(id);
		eventService.deleteById(id);
		return event;
	}

}
