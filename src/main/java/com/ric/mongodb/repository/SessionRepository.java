package com.ric.mongodb.repository;

import org.springframework.dao.DataAccessResourceFailureException;

import com.ric.domain.Session;



public interface SessionRepository {
	
   public void save(Session session) throws DataAccessResourceFailureException;
	
   public Session getSessionByUserName(String userName);
   
   public void delete(String key);
   
   public Session getSessionByID(String sessionId);
}
