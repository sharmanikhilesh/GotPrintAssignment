package org.gotprint.assignment.usernotes.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.internal.util.Base64;
import org.gotprint.assignment.usernotes.authentication.Authenticator;
import org.gotprint.assignment.usernotes.entity.NoteEntity;
import org.gotprint.assignment.usernotes.entity.UserEntity;
import org.gotprint.assignment.usernotes.exception.UnauthorizedException;
import org.gotprint.assignment.usernotes.model.LinkReference;
import org.gotprint.assignment.usernotes.model.Note;
import org.gotprint.assignment.usernotes.dbservice.NotesService;
import org.gotprint.assignment.usernotes.dbservice.UserService;


@Path("users/{email}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotesResource {
	
	private final String email;
	private static final String AUTHORIZATION_HEADER_KEY = "Authorization"; 
	
	
	private NotesService notesService;
	
	public NotesResource(){
		this.notesService = new NotesService();
		this.email = "";
	}
	
	public NotesResource(@PathParam("email") String email, @HeaderParam(AUTHORIZATION_HEADER_KEY) String authValue) {
		this.notesService = new NotesService();
		this.email = email;
				
		if(email==null){throw new WebApplicationException(Status.NOT_FOUND);}
		
		new UserService().createData();
		
		if(!Authenticator.authorizeUser(authValue, email))
			throw new UnauthorizedException();
		
		
	}
	
	@GET
	public List<Note> getAllNotesByUser(){
		List<NoteEntity> noteEntityList = notesService.getAllNotesByUser(email);
		List<Note> noteList = new ArrayList<Note>();
		for (NoteEntity notes : noteEntityList) {
			Note note = new Note(notes);
			note.getLinks().add(new LinkReference("self", "/users/" + email + "/" + note.getNoteId()));
			note.getLinks().add(new LinkReference("owner", "/users/" + email));	
			noteList.add(note);
		}
		return noteList;
	}
	
	@POST
	public void addNote(NoteEntity notes){
		notesService.addNoteForUser(email, notes);
	}
	
	@DELETE
	public void removeAllNotes(){
		notesService.removeAllNotes(email);
	}
	
	@DELETE
	@Path("/{noteid}")
	public void deleteNote(@PathParam("noteid") int noteID){
		notesService.verifyAndRemoveNote(email, noteID);
	}
	
	@PUT
	@Path("/{noteid}")
	public void updateNote(@PathParam("noteid") int noteID, NoteEntity newNote) {
		UserEntity user = new UserEntity();
		user.setEmail(email);
		newNote.setUser(user);
		notesService.verifyAndUpdateNote(email, noteID, newNote);
	}
	
}
