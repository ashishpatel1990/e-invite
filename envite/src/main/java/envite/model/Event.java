package envite.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "events")
public class Event 
{
	@Id
	@GeneratedValue
	Integer eid;
	String event_name;
	String event_message;
	
	@JsonIgnore
	@ManyToMany(mappedBy="events")
	List<invitation> invite;
	
	@ManyToOne
	user users;
	
	
	@Column(name="Picture")
	byte[] picture;
	
	
	
	
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public Integer getEid() {
		return eid;
	}
	public void setEid(Integer eid) {
		this.eid = eid;
	}
	public String getEvent_name() {
		return event_name;
	}
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	public String getEvent_message() {
		return event_message;
	}
	public void setEvent_message(String event_message) {
		this.event_message = event_message;
	}
	public List<invitation> getInvite() {
		return invite;
	}
	public void setInvite(List<invitation> invite) {
		this.invite = invite;
	}
	public user getUsers() {
		return users;
	}
	public void setUsers(user users) {
		this.users = users;
	}
	

}
