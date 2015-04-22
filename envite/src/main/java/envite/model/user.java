package envite.model;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "users")
public class user 
{
	@Id
	@GeneratedValue
	Integer id;
	String username;
	String password;
	String f_name;
	String l_name;
	String user_email;
	boolean enabled; 
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@JsonIgnore
	@OneToMany(mappedBy="users")
	Set<Event> events;
	
	@JsonIgnore
	@OneToOne(mappedBy="users")
	authorities authorizes;
	
	public user()
	{
		
	}
	
	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public authorities getAuthorizes() {
		return authorizes;
	}

	public void setAuthorizes(authorities authorizes) {
		this.authorizes = authorizes;
	}

	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}



}
