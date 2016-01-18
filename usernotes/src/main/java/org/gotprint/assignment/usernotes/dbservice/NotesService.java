package org.gotprint.assignment.usernotes.dbservice;

import java.util.Date;
import java.util.List;

import org.gotprint.assignment.usernotes.entity.NoteEntity;
import org.gotprint.assignment.usernotes.entity.UserEntity;
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
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery(NotesByUser);
		query.setString(EmailQueryVar, email);
		List<NoteEntity> notes = (List<NoteEntity>)query.list();
		session.close();
		return notes;
	}
	
	public void addNoteForUser(String email, NoteEntity note){
		UserEntity user = new UserEntity();
		user.setEmail(email);
		note.setUser(user);
		
		note.setCreateTime(new Date());
		note.setLastUpdateTime(new Date());
		
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(note);
		session.getTransaction().commit();
		session.close();

	}
	
	public void removeAllNotes(String email){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session.getNamedQuery(RemoveNotesByUser);
		query.setString(EmailQueryVar, email);
		query.executeUpdate();
		session.close();
	
	}
	
	public void removeNote(int noteID){
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		NoteEntity note = (NoteEntity) session.get(NoteEntity.class, noteID);
		session.delete(note);
		session.getTransaction().commit();
		session.close();
	}
	
	public void updateNote(int noteID, NoteEntity newNote){
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		NoteEntity note = (NoteEntity) session.get(NoteEntity.class, noteID);
		note.setNote(newNote.getNote());
		session.update(note);
		session.getTransaction().commit();
		session.close();
	}

}
