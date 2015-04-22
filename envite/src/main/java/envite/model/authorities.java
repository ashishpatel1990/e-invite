package envite.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "authorizes")
public class authorities 
{
	@Id
	@GeneratedValue
	Integer authorize_id;
	String username;
	String authority;
	
	@JsonIgnore
	@OneToOne
	user users;
	
	public authorities()
	{
		
	}

	public user getUsers() {
		return users;
	}

	public void setUsers(user users) {
		this.users = users;
	}

	public Integer getAuthorize_id() {
		return authorize_id;
	}
	public void setAuthorize_id(Integer authorize_id) {
		this.authorize_id = authorize_id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	

}
