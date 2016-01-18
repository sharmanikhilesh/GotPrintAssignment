package org.gotprint.assignment.usernotes.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name="note")
@NamedQueries({
	@NamedQuery(name="NoteEntity.byUserEmail", query="from NoteEntity where email = :email"),
	@NamedQuery(name="NoteEntity.delete.byUserEmail", query="delete from NoteEntity where email = :email")
})
public class NoteEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int noteId;
	@Column(length=50)
	private String title;
	@Column(length=1000)
	private String note;
	private Date createTime;
	private Date lastUpdateTime;
	
	@ManyToOne
	@JoinColumn(name="email")
	private UserEntity user;

	public NoteEntity() {}
	
	public NoteEntity(String title, String note, Date createTime,
			Date lastUpdateTime) {
		super();
		this.title = title;
		this.note = note;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
