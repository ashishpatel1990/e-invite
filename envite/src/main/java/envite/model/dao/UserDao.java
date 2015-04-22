package envite.model.dao;

import java.util.List;
import envite.model.Event;
import envite.model.authorities;
import envite.model.invitation;
import envite.model.user;

public interface UserDao {

	user getUser(String username);
	user getUser(int id);
	List<user> getUser();
   user saveUser( user user );
   List<Event> getEvent(Integer id);
	List<Event> getEvents(user u);
	boolean getLogin(String username, String pswrd);
	Event saveEvent(Event event,user u);
	Event saveEvent1(Event event);
	Event getEvent1(Integer id);
	invitation getInvite(Integer id);
	invitation get(Integer id);
	Event getMax();
	invitation saveInvite(invitation invite);
	invitation saveInvite1(invitation invit, Integer eid);
	List<invitation> getGuest(Integer eid);
	void delete(invitation invite);
	boolean getUserName(String username);
	List<invitation> getNames(String u);
	List<invitation> getNames();
	invitation getMessageId(String messageId);
	authorities saveAuthorize(authorities a,user u);
	
	List<Event> getEvents();

}