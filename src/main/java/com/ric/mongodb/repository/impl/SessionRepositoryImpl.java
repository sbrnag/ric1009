package com.ric.mongodb.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.ric.domain.Session;
import com.ric.mongodb.repository.SessionRepository;

@Repository("sessionRepository")
public class SessionRepositoryImpl implements SessionRepository {

	private static final Class<Session> clazz = Session.class;

	@Autowired
	private MongoTemplate mongo;

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}

	@Override
	public void save(final Session session)
			throws DataAccessResourceFailureException {
		mongo.insert(session);
	}

	@Override
	public void delete(final String key) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(key));
		mongo.findAndRemove(query, clazz);
		// if this throws any exception we need to catch and keep info in error
		// logs.
	}

	@Override
	public Session getSessionByUserName(final String userName) {
		/*
		 * BasicQuery query = new BasicQuery("{ " + "'username'" + ": '" +
		 * userName + "' }");
		 */
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(userName));
		Session session = mongo.findOne(query, clazz);
		return session;
	}

	@Override
	public Session getSessionByID(String sessionId) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(sessionId));
		Session session = null;
		try {
			session = mongo.findOne(query, clazz);
		}catch(DataAccessResourceFailureException ex) {
			//log the exception
			System.out.println("error" + ex.getMessage());
		}
		 
		return session;
	}

}
