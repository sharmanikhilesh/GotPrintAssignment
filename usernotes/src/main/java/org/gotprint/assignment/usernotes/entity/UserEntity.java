package org.gotprint.assignment.usernotes.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="user")
public class UserEntity {
	@Id
	@NotNull
	private String email;
	private String password;
	private Date createTime;
	private Date lastUpdateTime;
	
	@OneToMany(mappedBy="user")
	private Collection<NoteEntity> notes = new ArrayList<NoteEntity>();
	
	public UserEntity(){
		
	}
	
	public UserEntity(String email, String password, Date createTime,
			Date lastUpdateTime) {
		super();
		this.email = email;
		this.password = password;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
