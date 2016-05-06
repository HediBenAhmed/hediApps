package com.hediapps.rest.resources;

import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hediapps.service.TaskService;

@Component
@Path("/task")
public class TaskResource {
	private static final Logger logger = LoggerFactory.getLogger(TaskResource.class);

	@Autowired
	private TaskService taskService;

	/*
	 * @RequestMapping(value = "/dummy", method = RequestMethod.GET)
	 * public @ResponseBody Task getDummyTask(@CookieValue(value = "user")
	 * Cookie userCookie) { logger.info("Start getDummyTask"); Task task = new
	 * Task(); task.setTaskDate(new Date()); return task; }
	 * 
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	 * public @ResponseBody Task getTask(@CookieValue(value = "user") Cookie
	 * userCookie, @PathVariable("id") long taskId) { logger.info(
	 * "Start getTask. ID=" + taskId);
	 * 
	 * return taskService.readById(taskId); }
	 * 
	 * @RequestMapping(value = "/all", method = RequestMethod.GET)
	 * public @ResponseBody List<Task> getAllTasks(@CookieValue(value = "user")
	 * Cookie userCookie) { logger.info("Start getAllTasks."); return
	 * taskService.readAll(); }
	 * 
	 * @RequestMapping(value = "/create", method = RequestMethod.POST)
	 * public @ResponseBody Task createTask(@CookieValue(value = "user") Cookie
	 * userCookie, @RequestBody Task task) { logger.info("Start createTask.");
	 * 
	 * taskService.create(task); return task; }
	 * 
	 * @RequestMapping(value = "/update", method = RequestMethod.PUT)
	 * public @ResponseBody Task updateTask(@CookieValue(value = "user") Cookie
	 * userCookie, @RequestBody Task task) { logger.info("Start updateNode.");
	 * taskService.update(task); return task; }
	 * 
	 * @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	 * public @ResponseBody Task deleteTask(@CookieValue(value = "user") Cookie
	 * userCookie,
	 * 
	 * @PathVariable("id") long taskId) { logger.info("Start deleteTask."); Task
	 * task = taskService.readById(taskId); taskService.deleteById(taskId);
	 * return task; }
	 */
}
