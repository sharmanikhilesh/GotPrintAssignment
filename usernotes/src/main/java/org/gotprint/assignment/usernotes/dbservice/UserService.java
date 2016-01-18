package org.gotprint.assignment.usernotes.dbservice;


import java.util.List;

import org.gotprint.assignment.usernotes.entity.UserEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class UserService {
	
	private static SessionFactory sessionFactory = null;
	
    private void setSessionFactory() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }
	public UserService(){
		setSessionFactory();
	}
	
	public UserEntity getUserByEmail(String email){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		UserEntity user = (UserEntity) session.get(UserEntity.class, email);
		session.close();
		return user;
	}
	
	public List<UserEntity> getAllUsers(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.createQuery("from Message"); 
		List<UserEntity> users = (List<UserEntity>) query.list();
		session.close();
		System.out.println("Working.. 3");
		return users;
	}
}
