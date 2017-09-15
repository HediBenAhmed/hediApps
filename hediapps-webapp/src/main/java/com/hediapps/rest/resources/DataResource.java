package com.hediapps.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hediapps.backend.model.Data;
import com.hediapps.service.DataService;

@Component
@RequestMapping("/rest/datas")
public class DataResource {
	private static final Logger logger = LoggerFactory.getLogger(DataResource.class);

	@Autowired
	private DataService dataService;

	@RequestMapping(value = "dummy", method = RequestMethod.GET)
	@ResponseBody
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

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	@ResponseBody
	public Data getData(@PathVariable long id) {
		logger.info("Start getData. ID=" + id);

		return dataService.findByName("data1").iterator().next();
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Data> getAllDatas() {
		logger.info("Start getAllDatas.");
		Iterator<Data> datasIter = dataService.readAll().iterator();
		List<Data> copy = new ArrayList<Data>();
		while (datasIter.hasNext())
			copy.add(datasIter.next());

		return copy;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Data createData(Data data) {
		logger.info("Start createData.");

		dataService.create(data);
		return data;
	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	public Data updateData(Data data) {
		logger.info("Start updateNode.");
		dataService.update(data);
		return data;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public Data deleteData(@PathVariable long id) {

		logger.info("Start deleteData.");
		Data data = dataService.readById(id);
		dataService.deleteById(id);
		return data;
	}

}
