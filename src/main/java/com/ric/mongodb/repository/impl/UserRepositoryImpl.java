package com.ric.mongodb.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;

import com.ric.domain.User;
import com.ric.mongodb.repository.UserRepository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

	// static Logger log = Logger.getLogger(UsersRepository.class);
	
	private static final Class<User> clazz = User.class;

	@Autowired
	private MongoTemplate mongo;

	public MongoTemplate getMongo() {
		return mongo;
	}

	public void setMongo(MongoTemplate mongo) {
		this.mongo = mongo;
	}

	@Override
	public void save(final User user) throws DataAccessResourceFailureException, DuplicateKeyException {
		mongo.insert(user);
	}

	@Override
	public boolean authenticate(final String userName, final String password)
			throws DataAccessResourceFailureException {

		boolean validUser = false;
		BasicQuery querry = new BasicQuery("{ " + "'_id'" + ": '" + userName
				+ "'," + "'password'" + ": '" + password + "'}");
		User user = mongo.findOne(querry, clazz);
		if (user != null) {
			validUser = true;
		} 
		return validUser;
	}

	public User findByUserName(final String userName)
			throws DataAccessResourceFailureException {
		BasicQuery query = new BasicQuery("{ " + "'_id'" + ": '" + userName
				+ "' }");
		User user = mongo.findOne(query, clazz);
		return user;
	}

	@Override
	public void delete(User user) {
		mongo.remove(user);
		
	}

	@Override
	public void update(User user) throws DataAccessResourceFailureException {
		mongo.save(user);
	}

}
