package envite.model.dao.jpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import envite.model.Event;
import envite.model.authorities;
import envite.model.invitation;
import envite.model.user;
import envite.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public user getUser( String username )
    {
        return entityManager.createQuery( "from user u where u.username= :username",user.class).setParameter("username",username).getSingleResult();
  }

	@Override
	public List<user> getUser() 
	{
	     return entityManager.createQuery( "from user order by id asc",
	             user.class ).getResultList();
	     
	}


	// Search
	
	@Override
	public List<invitation> getNames()
	{
		return entityManager.createQuery("from invitation order by invite_id asc",invitation.class).getResultList();
	}
	
	
	@PreAuthorize("principal.username==#user.username")
	@Transactional
	@Override
	public user saveUser(user user)
	{
	      return entityManager.merge( user );
	      
	}

	
	@Transactional
	@Override
	public Event saveEvent(Event event,user u)
	{
		event.setUsers(u);
		  return entityManager.merge(event);
	      
	}
	
	@Override
	public boolean getLogin(String username, String pswrd)
	{
		boolean b=false;
		try
		{
		  user u=entityManager.createQuery( "from user u where u.username= :username and u.password= :pswrd",user.class ).setParameter("username", username).setParameter("pswrd", pswrd).getSingleResult();
		  if(u!=null)
		  {
			  return true;
		  }
		}
		catch(Exception e)
		{
			return false;
		}
		return b;
	}
	
	
	public boolean getUserName(String username)
	{
		boolean b=false;
		try
		{
			  user u=entityManager.createQuery( "from user u where u.username= :username",user.class ).setParameter("username", username).getSingleResult();
				if(u!=null)
				{
					  System.out.println("its true");
					return true;
				}
		}
		catch(Exception e)
		{
			return false;
		}
		System.out.println("its flase");
		
		return b;
	}

	@Override
	public List<Event> getEvent(Integer id) 
	{
	       
	    return entityManager.createQuery( "from Event e where e.eid= :eid",Event.class).setParameter("eid",id).getResultList();
	    
	}
	
	@Override
	public List<Event> getEvents(user u) 
	{
	       
	    return entityManager.createQuery( "from Event e where e.users= :user",Event.class).setParameter("user",u).getResultList();
	    
	}
	
	@Override
	public List<invitation> getGuest(Integer id)
	{
		List<invitation> in=getEvent(id).get(0).getInvite();
		return in;
	}

	 @Override
	    public Event getEvent1( Integer id )
	    {
	        return entityManager.find( Event.class, id );
	    }
	
	@Override
	public user getUser(int id) 
	{
        return entityManager.createQuery( "from user u where u.id= :id",user.class).setParameter("id",id).getSingleResult();

	}

	@Transactional
	@Override
	public invitation saveInvite(invitation in) 
	{
		  return entityManager.merge(in);
	
	}
	
	
	@Override
	public Event saveEvent1(Event event) {
		return entityManager.merge(event);
	      
	}

	@Override
	public Event getMax() 
	{
	    return entityManager.createQuery( "from Event e order by eid desc",Event.class).getResultList().get(0);
	}
	
	@Transactional
	@Override
	public void delete(invitation invite) 
	{
		 entityManager.remove(invite);
	}

	@Transactional
	@Override
	public invitation saveInvite1(invitation invit, Integer eid) 
	{
		
		invit.setEvents(getEvent(getMax().getEid()));
		
		  return entityManager.merge(invit);
	}

	@Override
	public invitation getInvite(Integer id) 
	{
	       return entityManager.find( invitation.class, id );
	  	 
	}


	@Override
	@Transactional
	public invitation get(Integer id) {
		invitation i=entityManager.createQuery( "from invitation i where i.invite_id= :id",invitation.class).setParameter("id",id).getSingleResult();
	     i.setStatus(true);
	      return  entityManager.merge(i);
	}

	@Override
	public List<invitation> getNames(String u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public invitation getMessageId(String messageId) {
		invitation i=entityManager.createQuery( "from invitation i where i.message_id= :id",invitation.class).setParameter("id",messageId).getSingleResult();
			return i;
	}

	@Transactional
	@Override
	public authorities saveAuthorize(authorities a,user u) 
	{
		System.out.println(u.getId());
		System.out.println("Id Printed");
		a.setUsername(u.getUsername());
		a.setAuthority("ROLE_USER");
		a.setUsers(u);
		return entityManager.merge(a);
	}

	@Override
	public List<Event> getEvents() {
	    return entityManager.createQuery( "from Event ",Event.class).getResultList();
	}


	
  
}