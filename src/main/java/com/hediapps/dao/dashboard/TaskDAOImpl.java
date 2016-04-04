package com.hediapps.dao.dashboard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hediapps.dao.DAO;
import com.hediapps.model.dashboard.Task;

public class TaskDAOImpl implements DAO<Task> {
	@Autowired
	MongoTemplate mongoTemplate;

	public Task create(Task data) {

		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	public Task findById(long id) {

		Task data = mongoTemplate.findById(id, Task.class);
		System.out.println(data);

		return data;
	}

	public Task update(Task object) {
		Task data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	public Task delete(long id) {
		Task data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		Task lastData = mongoTemplate.findOne(query, Task.class);

		System.out.println(lastData);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	public List<Task> findAll() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(Task.class.getName()));

		return mongoTemplate.find(query, Task.class);
	}
}
