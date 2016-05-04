package com.hediapps.controller.dashboard.datas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.controller.LoginController;
import com.hediapps.model.dashboard.Data;
import com.hediapps.service.dashboard.DataService;

@Controller
@RequestMapping("/dataService")
public class DataController {
	private static final Logger logger = LoggerFactory.getLogger(DataController.class);

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "/dummy", method = RequestMethod.GET)
	public @ResponseBody Data getDummyData(@CookieValue(value = "user") Cookie userCookie) {
		logger.info("Start getDummyData");
		Data data = new Data();
		data.setId(0);
		data.setName("data");
		Map<String, Double> values = new HashMap<String, Double>();
		values.put("a", 0.5d);
		data.setValues(values);
		return data;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Data getData(@CookieValue(value = "user") Cookie userCookie, @PathVariable("id") long dataId) {
		logger.info("Start getData. ID=" + dataId);

		return dataService.readById(dataId);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody List<Data> getAllDatas(@CookieValue(value = "user") Cookie userCookie) {
		logger.info("Start getAllDatas.");

		if (LoginController.checkToken(userCookie.getValue()))
			return dataService.readAll();
		else
			return null;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody Data createData(@CookieValue(value = "user") Cookie userCookie, @RequestBody Data data) {
		logger.info("Start createData.");

		dataService.create(data);
		return data;
	}

	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public @ResponseBody Data updateData(@CookieValue(value = "user") Cookie userCookie, @RequestBody Data data) {
		logger.info("Start updateNode.");
		dataService.update(data);
		return data;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Data deleteData(@CookieValue(value = "user") Cookie userCookie,
			@PathVariable("id") long dataId) {
		logger.info("Start deleteData.");
		Data data = dataService.readById(dataId);
		dataService.deleteById(dataId);
		return data;
	}

}
