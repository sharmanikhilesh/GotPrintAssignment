package org.gotprint.assignment.usernotes.model;

	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

	import org.gotprint.assignment.usernotes.entity.NoteEntity;

	public class Note {
		
		private int noteId;
		private String title;
		private String note;
		private Date createTime;
		private Date lastUpdateTime;
		private List<LinkReference> links = new ArrayList<LinkReference>();
		
		public List<LinkReference> getLinks() {
			return links;
		}

		public void setLinks(List<LinkReference> links) {
			this.links = links;
		}
		
		public Note(){
			
		}
		
		public Note(NoteEntity notes){
			this.noteId = notes.getNoteId();
			this.note = notes.getNote();
			this.title = notes.getTitle();
			this.createTime = notes.getCreateTime();
			this.lastUpdateTime = notes.getLastUpdateTime();
		}
		
		public Note(int noteId, String note) {
			super();
			this.noteId = noteId;
			this.note = note;
		}

		public int getNoteId() {
			return noteId;
		}

		public void setNoteId(int noteId) {
			this.noteId = noteId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getNote() {
			return note;
		}

		public void setNote(String note) {
			this.note = note;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public Date getLastUpdateTime() {
			return lastUpdateTime;
		}

		public void setLastUpdateTime(Date lastUpdateTime) {
			this.lastUpdateTime = lastUpdateTime;
		}


}
