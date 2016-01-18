package org.gotprint.assignment.usernotes.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.gotprint.assignment.usernotes.dbservice.NotesService;
import org.gotprint.assignment.usernotes.dbservice.UserService;
import org.gotprint.assignment.usernotes.entity.NoteEntity;
import org.gotprint.assignment.usernotes.model.Note;



@Path("users/{email}/notes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotesResource {
	
	
	private NotesService notesService;
	
	public NotesResource(){
		this.notesService = new NotesService();
		new UserService().createData();
	}
	
	@GET
	public List<Note> getAllNotesByUser(@PathParam("email") String email){
		List<NoteEntity> noteEntityList = notesService.getAllNotesByUser(email);
		List<Note> noteList = new ArrayList<Note>();
		for (NoteEntity notes : noteEntityList) {			
			noteList.add(new Note(notes));
		}
		return noteList;
	}
	
}
