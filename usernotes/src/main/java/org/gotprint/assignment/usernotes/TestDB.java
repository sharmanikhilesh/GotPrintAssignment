package org.gotprint.assignment.usernotes;

import java.util.Date;

import org.gotprint.assignment.usernotes.dbservice.HibernateUtils;
import org.gotprint.assignment.usernotes.dbservice.UserService;
import org.gotprint.assignment.usernotes.entity.UserEntity;
import org.hibernate.Session;

public class TestDB {

	public static void main(String[] args) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		session.beginTransaction();		
		Date dt = new Date();
		session.saveOrUpdate(new UserEntity("one@one.com", "passone", dt,dt));
		//session.saveOrUpdate(new UserEntity("two@two.com", "passtwo", dt,dt));
		//session.saveOrUpdate(new UserEntity("three@three.com", "passthree", dt,dt));
		session.getTransaction().commit();
		session.close();
		
		UserEntity user = new UserService().getUserByEmail("two@two.com");
		System.out.println(user.getPassword() + ", " + user.getCreateTime());

	}

}
