package com.hediapps.backend.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.hediapps.backend.model.Email;
import com.hediapps.backend.model.Message;

@Repository
public class MessageDAOImpl implements DAO<Message> {
	@Autowired
	MongoTemplate mongoTemplate;

	public Message create(Message data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	public Message findById(long id) {

		// Query query = new Query();
		// query.addCriteria(Criteria.where("name").is(data.getName())
		// .and("value").is(data.getValue()));
		//
		// List<Data> datas = mongoTemplate.find(query, Data.class);
		Message data = mongoTemplate.findById(id, Message.class);

		return data;
	}

	public Message update(Message object) {
		Message data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	public Message delete(long id) {
		Message data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		Message lastData = mongoTemplate.findOne(query, Message.class);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	public List<Message> findAll() {

		return findAllEmails();
	}

	public List<Message> findAllEmails() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(Email.class.getName()));

		return mongoTemplate.find(query, Message.class);
	}

	public List<Email> findByToUserId(long toUser) {

		Query query = new Query();
		query.addCriteria(Criteria.where("toUser").is(toUser));

		return mongoTemplate.find(query, Email.class);
	}
}