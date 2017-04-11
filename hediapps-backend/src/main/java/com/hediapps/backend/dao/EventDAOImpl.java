package com.hediapps.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.backend.model.Event;
import com.hediapps.backend.model.SystemEvent;

@Repository
public class EventDAOImpl implements DAO<Event> {
	@Autowired
	MongoTemplate mongoTemplate;

	@Transactional
	public Event create(Event data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	@Transactional(readOnly = true)
	public Event findById(long id) {

		// Query query = new Query();
		// query.addCriteria(Criteria.where("name").is(data.getName())
		// .and("value").is(data.getValue()));
		//
		// List<Data> datas = mongoTemplate.find(query, Data.class);
		Event data = mongoTemplate.findById(id, Event.class);

		return data;
	}

	@Transactional
	public Event update(Event object) {
		Event data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	@Transactional
	public Event delete(long id) {
		Event data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		Event lastData = mongoTemplate.findOne(query, Event.class);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	@Transactional(readOnly = true)
	public List<Event> findAll() {

		List<Event> notifications = findAllSystemEvents();

		return notifications;
	}

	@Transactional(readOnly = true)
	public List<Event> findAllSystemEvents() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(
				SystemEvent.class.getName()));

		return mongoTemplate.find(query, Event.class);
	}
}