package org.gotprint.assignment.usernotes.dbservice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.gotprint.assignment.usernotes.entity.NoteEntity;
import org.gotprint.assignment.usernotes.entity.UserEntity;
import org.gotprint.assignment.usernotes.exception.UnauthorizedException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class NotesService {
	
	private static SessionFactory sessionFactory = null;
    
    private final String NotesByUser = "NoteEntity.byUserEmail";
    private final String RemoveNotesByUser = "NoteEntity.delete.byUserEmail";
    private final String EmailQueryVar = "email";
    
    public NotesService(){
    	setSessionFactory();
    }
	
    private void setSessionFactory() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }
    
	public List<NoteEntity> getAllNotesByUser(String email){
		
		Session session = null;
		
		List<NoteEntity> notes = new ArrayList<NoteEntity>();
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery(NotesByUser);
			query.setString(EmailQueryVar, email);
			notes = (List<NoteEntity>)query.list();
		} catch (HibernateException e) {			
			e.printStackTrace();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
		
		return notes;
	}
	
	public void addNoteForUser(String email, NoteEntity note){
		UserEntity user = new UserEntity();
		user.setEmail(email);
		note.setUser(user);
		note.setCreateTime(new Date());
		note.setLastUpdateTime(new Date());
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(note);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}
	
	public void removeAllNotes(String email){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			Query query = session.getNamedQuery(RemoveNotesByUser);
			query.setString(EmailQueryVar, email);
			query.executeUpdate();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}		
	}
	
	public void verifyAndRemoveNote(String email, int noteID) {
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			NoteEntity note = (NoteEntity) session.get(NoteEntity.class, noteID);
			if(!email.equals(note.getUser().getEmail())){
				session.close();
				throw new UnauthorizedException();
			}
			session.delete(note);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}
	
	public void verifyAndUpdateNote(String email, int noteID, NoteEntity newNote){
		Session session = null;
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
			NoteEntity note = (NoteEntity) session.get(NoteEntity.class, noteID);
			if(!email.equals(note.getUser().getEmail())){
				session.close();
				throw new UnauthorizedException();
			}
			note.setLastUpdateTime(new Date());
			note.setNote(newNote.getNote());
			session.update(note);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		}
		finally{
			if(session != null)
				session.close();
		}
	}

}
