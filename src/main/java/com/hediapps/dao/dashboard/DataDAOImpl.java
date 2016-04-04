package com.hediapps.dao.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hediapps.dao.DAO;
import com.hediapps.model.dashboard.Data;

public class DataDAOImpl implements DAO<Data> {
	@Autowired
	MongoTemplate mongoTemplate;

	public Data create(Data data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	public Data findById(long id) {

		// Query query = new Query();
		// query.addCriteria(Criteria.where("name").is(data.getName())
		// .and("value").is(data.getValue()));
		//
		// List<Data> datas = mongoTemplate.find(query, Data.class);
		Data data = mongoTemplate.findById(id, Data.class);
		System.out.println(data);

		return data;
	}

	public Data update(Data object) {
		Data data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	public Data delete(long id) {
		Data data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		Data lastData = mongoTemplate.findOne(query, Data.class);

		System.out.println(lastData);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	public List<Data> findAll() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(Data.class.getName()));

		return mongoTemplate.find(query, Data.class);
	}

}