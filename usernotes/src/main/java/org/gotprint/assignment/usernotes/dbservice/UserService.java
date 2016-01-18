package org.gotprint.assignment.usernotes.dbservice;


import java.util.Date;
import java.util.List;

import org.gotprint.assignment.usernotes.entity.NoteEntity;
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
	
	public void createData(){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Date dt = new Date();
		session.saveOrUpdate(new UserEntity("one@one.com", "passone", dt,dt));
		session.saveOrUpdate(new UserEntity("two@two.com", "passtwo", dt,dt));
		session.saveOrUpdate(new UserEntity("three@three.com", "passthree", dt,dt));
		session.getTransaction().commit();
		
		/*NoteEntity note = new NoteEntity("First Note", "Writing first note", dt, dt);
		NoteEntity note2 = new NoteEntity("Sec Note", "Writing sec note", dt, dt);
		NotesService nServe = new NotesService(); 
		nServe.addNoteForUser("one@one.com", note);
		nServe.addNoteForUser("one@one.com", note2);*/
		session.close();
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
