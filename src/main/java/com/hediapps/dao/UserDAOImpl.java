package com.hediapps.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hediapps.model.User;

@Repository
public class UserDAOImpl implements DAO<User> {
	@Autowired
	MongoTemplate mongoTemplate;

	@Transactional
	public User create(User data) {
		data.setId(getNextSequence());
		mongoTemplate.insert(data);

		return data;
	}

	@Transactional(readOnly = true)
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

	@Transactional
	public User update(User object) {
		User data = findById(object.getId());

		if (data == null) {
			return null;
		}

		mongoTemplate.save(object);

		return object;
	}

	@Transactional
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

	@Transactional(readOnly = true)
	public List<User> findAll() {

		Query query = new Query();
		query.addCriteria(Criteria.where("_class").is(User.class.getName()));

		return mongoTemplate.find(query, User.class);
	}

	@Transactional(readOnly = true)
	public User findByUserNameAndPassword(String userName, String password) {

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userName).andOperator(Criteria.where("password").is(password)));

		return mongoTemplate.findOne(query, User.class);
	}

	@Transactional(readOnly = true)
	public User findByUserName(String username) {

		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));

		User user = mongoTemplate.findOne(query, User.class);

		return user;
	}
}