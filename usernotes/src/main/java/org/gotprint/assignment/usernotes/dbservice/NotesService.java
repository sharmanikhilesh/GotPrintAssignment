package org.gotprint.assignment.usernotes.dbservice;


import org.hibernate.SessionFactory;

public class NotesService {
	
	private static SessionFactory sessionFactory = null;
    
    
    public NotesService(){
    	setSessionFactory();
    }
	
    private void setSessionFactory() {
        sessionFactory = HibernateUtils.getSessionFactory();
    }

}
