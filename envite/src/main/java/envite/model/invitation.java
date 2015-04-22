package envite.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invite")
public class invitation 
{
	@Id
	@GeneratedValue
	Integer invite_id;
	String accepted;
	String messageToHost;
	String guestName;
	String guestEmail;
	boolean status;
	String message_id;
	
	

	public String getMessage_id() {
		return message_id;
	}

	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@ManyToMany
	List<Event> events;
	
	

	public Integer getInvite_id() {
		return invite_id;
	}

	public void setInvite_id(Integer invite_id) {
		this.invite_id = invite_id;
	}

	

	public String getAccepted() {
		return accepted;
	}

	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}

	public String getMessageToHost() {
		return messageToHost;
	}

	public void setMessageToHost(String messageToHost) {
		this.messageToHost = messageToHost;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestEmail() {
		return guestEmail;
	}

	public void setGuestEmail(String guestEmail) {
		this.guestEmail = guestEmail;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}


	
	
	

}
