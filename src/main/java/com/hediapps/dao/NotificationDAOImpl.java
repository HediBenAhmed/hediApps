package com.hediapps.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.model.Notification;
import com.hediapps.model.SystemEvent;

@Repository
public class NotificationDAOImpl implements DAO<Notification> {
	@Autowired
	MongoTemplate mongoTemplate;

	@Transactional
	public Notification create(Notification data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	@Transactional(readOnly = true)
	public Notification findById(long id) {

		// Query query = new Query();
		// query.addCriteria(Criteria.where("name").is(data.getName())
		// .and("value").is(data.getValue()));
		//
		// List<Data> datas = mongoTemplate.find(query, Data.class);
		Notification data = mongoTemplate.findById(id, Notification.class);

		return data;
	}

	@Transactional
	public Notification update(Notification object) {
		Notification data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	@Transactional
	public Notification delete(long id) {
		Notification data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		Notification lastData = mongoTemplate.findOne(query, Notification.class);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	@Transactional(readOnly = true)
	public List<Notification> findAll() {

		List<Notification> notifications = findAllSystemEvents();

		return notifications;
	}

	@Transactional(readOnly = true)
	public List<Notification> findAllSystemEvents() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(SystemEvent.class.getName()));

		return mongoTemplate.find(query, Notification.class);
	}
}