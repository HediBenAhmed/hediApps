package com.hediapps.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.hediapps.dao.DAO;
import com.hediapps.model.User;

public class UserDAOImpl implements DAO<User> {
	@Autowired
	MongoTemplate mongoTemplate;

	public User create(User data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	public User findById(long id) {

		// Query query = new Query();
		// query.addCriteria(Criteria.where("name").is(data.getName())
		// .and("value").is(data.getValue()));
		//
		// List<Data> datas = mongoTemplate.find(query, Data.class);
		User data = mongoTemplate.findById(id, User.class);
		System.out.println(data);

		return data;
	}

	public User update(User object) {
		User data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	public User delete(long id) {
		User data = findById(id);
		if (data != null)
			mongoTemplate.remove(data);
		return data;
	}

	private long getNextSequence() {

		Query query = new Query();
		query.limit(1);
		query.with(new Sort(Sort.Direction.DESC, "_id"));

		User lastData = mongoTemplate.findOne(query, User.class);

		System.out.println(lastData);

		if (lastData == null)
			return 1l;
		else
			return lastData.getId() + 1l;
	}

	public List<User> findAll() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(User.class.getName()));

		return mongoTemplate.find(query, User.class);
	}

	public User findByLoinAndPassword(String login, String password) {

		Query query = new Query();
		query.addCriteria(Criteria.where("login").is(login).andOperator(Criteria.where("password").is(password)));

		return mongoTemplate.findOne(query, User.class);
	}
}