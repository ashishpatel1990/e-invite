package envite.web.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonIgnoreType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import envite.model.Event;
import envite.model.authorities;
import envite.model.invitation;
import envite.model.user;
import envite.model.dao.UserDao;
import envite.security.SecurityUtils;
import envite.web.validator.UserValidator;

@Controller
@SessionAttributes("user1")
@JsonAutoDetect
@JsonIgnoreType
@JsonIgnoreProperties
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    UserValidator userValidator;
    
    boolean b=false;
    
    
   //LOGIN 
    @RequestMapping(value = "/user/Login.html", method = RequestMethod.GET)
    public String Login( ModelMap models)
    {
    	models.put("user", new user());
    	return "Login";
    }
    
    @RequestMapping(value = "/user/Login.html", method = RequestMethod.POST)
    public String Login(  @RequestParam String username,@RequestParam String password,@ModelAttribute user u,HttpSession http,BindingResult bindingResult  )
    {	
    	   userValidator.validateLogin( u, bindingResult );
           if( bindingResult.hasErrors() ) return "Login";

    	 	  if(userDao.getLogin(username, password)==true)
        	  {	
        		  user u1=userDao.getUser(username);
        		  http.setAttribute("user",u1.getId() );
        		  return "redirect:/user/Display.html";
        	  }
    	 	  else
        return "redirect:/user/Login.html";
        
    }
    
    //LOGOUT
    @RequestMapping(value = "/user/Logout.html", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null) {
            session.removeAttribute("user");
        }
        session.invalidate();
        return "Logout";
    }
    
    @RequestMapping(value = "/user/Logout.html", method = RequestMethod.POST)
    public String logout() 
    {
        return "redirect:/user/Login.html";
    }
    
    
   
    //SIGNUP   
    @RequestMapping(value = "/user/Signup.html", method = RequestMethod.GET)
    public String signup( ModelMap models)
    {
    	
    	models.put("users", new user() );
   	 
    	return "Signup";
    }
  
    @RequestMapping(value = "/user/Signup.html", method = RequestMethod.POST)
    public String signup(@ModelAttribute("users") user users, BindingResult bindingResult,HttpSession http,ModelMap models )
    {
        userValidator.validate( users, bindingResult );
        if( bindingResult.hasErrors() ) return "Signup";
        String username=users.getUsername();
    
        System.out.println(username);
         if(userDao.getUserName(username)!=true)
         {
        	 System.out.println("true");
        	 users.setEnabled(true);
        		userDao.saveUser( users );
        		user us=userDao.getUser(username);
        		authorities a=new authorities();
           	userDao.saveAuthorize(a,us);
           
           	return "redirect:/user/Display.html";
        }
        System.out.println("flase");
     	return "redirect:/user/Signup.html";
    }

    
   //Display All User List 
    @RequestMapping("/user/list.html")
    public String list( ModelMap models )
    {
        models.put( "users", userDao.getUser() );
        return "list";
    }
    
       
    //CREATE EVENT
    @RequestMapping(value = "/user/Create.html", method = RequestMethod.GET)
    public String Create( ModelMap models)
    {
    	models.put("events", new Event());
    	return "Create";
    }
    
    @RequestMapping(value="/user/Create.html", method= RequestMethod.POST)
    public String Create( @ModelAttribute("events") Event events, @RequestParam(value="pic", required=false)MultipartFile pic,@RequestParam Integer id, ModelMap models,HttpSession htpp,BindingResult bindingResult ) throws IOException 
    {
    	
    	  userValidator.validateEvent( events, bindingResult );
          if( bindingResult.hasErrors() ) return "Create";

    	user u=userDao.getUser(id);
    	
		if(!pic.isEmpty())
		{
			byte[] getPicture = pic.getBytes();
		
			events.setPicture(getPicture);
			}
		else
		{
			
		}
		userDao.saveEvent(events,u);
    	htpp.setAttribute("eid", events);

		return "redirect:/user/Add.html";
    	
    }
    
    
    // Add Multiple Guest Page
    @RequestMapping(value = "/user/Add.html", method = RequestMethod.GET)
    public String Add( ModelMap models)
    {
    	return "Add";
    }
    
    
    
    
    // Adding Guest
    @RequestMapping(value="/user/addGuest.html",method = RequestMethod.GET)
    public String Add(ModelMap models,HttpSession http)
    {
    	System.out.println("COntroller called");
    	Event e=userDao.getMax();
    	http.setAttribute("eid", e.getEid());
    	models.put("invite", new invitation());
    	return "addGuest";
      }
    
    @RequestMapping(value="/user/addGuest.html",method = RequestMethod.POST)
    public String Add(@ModelAttribute("invite") invitation invite,ModelMap models,HttpSession http,SessionStatus sessionStatus,BindingResult bindingResult )
    {
    	  userValidator.validateGuest( invite, bindingResult );
          if( bindingResult.hasErrors() ) return "addGuest";

 		 String uuid = UUID.randomUUID().toString();
//    	 String message=e.getEvent_message()+" "+"http://localhost:8080/envite/user/Decide/"+uuid+".html";

    	Integer eid=(Integer) http.getAttribute("eid");
    	System.out.println(eid);
    	invite.setStatus(false);
    	invite.setMessage_id(uuid);
        userDao.saveInvite1(invite,eid);
        return "redirect:/user/Add.html";
    }
    
    
    
    // Search Guest using AJAX
    @RequestMapping(value="/getTags.html", method = RequestMethod.GET,headers="Accept=*/*")
	public @ResponseBody
	List<invitation> getTags(@RequestParam("term") String query) 

	{
		List<String> r=new ArrayList<String>();

		List<invitation> result = new ArrayList<invitation>();
		System.out.println(query);
		try
		{
		System.out.println("calles");
		
		List<invitation> result1=userDao.getNames();
		
		for(invitation i: result1)
		{
			if(i.getGuestName().contains(query))
			{
				result.add(i);
				r.add(i.getGuestName());
				r.add(i.getGuestEmail());
				//System.out.println(result);
				System.out.println(i.getGuestName());
				
				
			}
				
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
		
		//return simulateSearchResult(result1);
		
	}
     
    
 
    //  Display Page for Each User
    @RequestMapping(value = "/user/Display.html", method = RequestMethod.GET)
    public String Display( ModelMap models,HttpSession http)
    {
    	if(SecurityUtils.isAuthenticated())
    	  http.setAttribute("user",userDao.getUser(SecurityUtils.getUsername()).getId() );
    	return "Display";
    }
   
    
    //  View Event Page
    @RequestMapping(value="/user/View1.html")
    public String View1( ModelMap models )
    {
    		models.put( "events", userDao.getEvents(  ) );
        return "View1";
    }
    
    
    
    @RequestMapping(value = "/user/view1.html")
    public String view1(@PathVariable ("eid") Integer eid,HttpServletResponse http )
    {
    	
		return "view1";
    	
    }
    
  
    //Accept or Reject
    @RequestMapping(value="/user/Decide/{messageId}.html",method=RequestMethod.GET)
    public String Decide(ModelMap models)
    {
    	System.out.println("GET");
    	return "Decide";
    }
    
    @RequestMapping(value="/user/Decide/{messageId}.html",method=RequestMethod.POST)
    public String Decide(@PathVariable ("messageId") String messageId,@RequestParam String Decide,ModelMap models)
    {
    	System.out.println("POST");
    	System.out.println(messageId);
    	if( Decide.equals("yes") )
    	{
    		invitation i=userDao.getMessageId(messageId);
    		i.setAccepted("Coming");
    		userDao.saveInvite(i);
    		System.out.println("yes");
    	       //handle save
    	    }
    	    else if( Decide.equals("no") ){
    	    	System.out.println("no");
    	    	
    	    	invitation i=userDao.getMessageId(messageId);
        		i.setAccepted("Not Coming");
    	    	userDao.saveInvite(i);
        		 }
    	return "Decide";
    }
    
    
    // Details of Guest and Event
    @RequestMapping(value="/user/Details.html", method = RequestMethod.GET)
    public String Details(@RequestParam Integer eid, ModelMap models )
    {
    	models.put("user", userDao.getUser(SecurityUtils.getUsername()));
        models.put( "events", userDao.getEvent1( eid ) );
        List<invitation> i=userDao.getGuest(eid);
        models.put("invite", i);
        return "Details";
    }
    
    
    @RequestMapping(value="/user/{eid}.html")
    public String Details(@PathVariable ("eid") Integer eid, HttpServletResponse http )
    {
    	System.out.println("called");
    	System.out.println(eid);
    	Event e=userDao.getEvent1(eid);
    	System.out.println(e.getEvent_message());
    	byte[] pic=e.getPicture();
    	http.setContentType("image/jpeg");
    	try
    	{
    	http.getOutputStream().write(pic);
    	http.getOutputStream().flush();
    	}
    	catch(Exception ee)
    	{
    		ee.printStackTrace();
    	}
    	
        return null;
    }
    
    
    //Email
    
    @RequestMapping(value="/user/Email.html", method = RequestMethod.GET)
    public String Email(@RequestParam Integer eid, ModelMap models )
    {
    	models.put("user", userDao.getUser(SecurityUtils.getUsername()));
    	models.put("eid", eid);
        models.put( "events", userDao.getEvent1( eid ) );
        List<invitation> i=userDao.getGuest(eid);
        models.put("invite", i);
        return "Email";
    }
    
    @RequestMapping(value="/user/Email.html", method = RequestMethod.POST)
    public String Email(@RequestParam String to[],HttpSession http,@RequestParam Integer event,ModelMap models ) throws MessagingException
    {
   	user u=userDao.getUser((Integer)http.getAttribute("user"));	
    System.out.println(event);
    models.put( "events", userDao.getEvent1( event ) );
    Event e=userDao.getEvent1(event);
    
    String sub=e.getEvent_name();
    String from=u.getUser_email();
    List<invitation> in=userDao.getGuest(e.getEid());
    
        Properties props = System.getProperties();
        props.put( "mail.smtp.host", "localhost" );
        Session session = Session.getInstance( props );
        Message msg = new MimeMessage( session );
//get invitation of particular event.
        
        try
        {
        	        	        	    
        	 for(int i=0;i<to.length;i++)
	            {
        		    
        		 for(invitation ij:in)
        		 {
        			 if(ij.getGuestEmail().equals(to[i]))
        			 {
            	 String message=e.getEvent_message()+" "+"http://localhost:8080/envite/user/Decide/"+ij.getMessage_id()+".html";
            	 
            	 System.out.println(message);
            	 
	                 msg.setFrom( new InternetAddress( from ) );
	                 System.out.println(from);
	                 msg.setRecipient( RecipientType.TO, new InternetAddress( to[i] ) );
	                 System.out.println(to[i]);
	                 msg.setSubject( sub );
	                 msg.setText( message );
	                 
	                 Transport.send( msg );
	              
	                 //code for Not sending Guest Again
	                 
	                 for(invitation g:in)
	                 {
	                	 if(g.getGuestEmail().equals(to[i]))
	                	 {
	                		 userDao.get(g.getInvite_id());
	                	 }
	                 }
	                 
        			 }
        		 
	            }
	            }
        	
	     } 

        catch( Exception ex )
        {
        	ex.printStackTrace();
       }
  
        return "redirect:/user/Display.html";
    }
    
    
    
    
    // Edit EventName and EventMessage
    @RequestMapping(value = "/user/edit.html", method = RequestMethod.GET)
    public String edit( @RequestParam Integer eid, ModelMap models ,HttpSession s)
    {
    	s.setAttribute("eeid", eid);
        models.put("user", userDao.getUser(SecurityUtils.getUsername()));
    	models.put( "events", userDao.getEvent1( eid ) );
        List<invitation> i=userDao.getGuest(eid);
        models.put("invite", i);
         return "edit";
    }
    
    @RequestMapping(value = "/user/edit.html", method = RequestMethod.POST)
    public String edit( @RequestParam(value="pic", required=false)MultipartFile pic,@ModelAttribute("events") Event events,@ModelAttribute user user, @ModelAttribute invitation invite, BindingResult bindingResult,
        SessionStatus sessionStatus ) throws IOException
    {
   	  userValidator.validateEvent( events, bindingResult );
      if( bindingResult.hasErrors() ) return "Create";

      if(!pic.isEmpty())
		{
			byte[] getPicture = pic.getBytes();
		
			events.setPicture(getPicture);
			}
		else
		{
	 		Event e=userDao.getEvent1(events.getEid());
			byte[] b=e.getPicture();
			events.setPicture(b);
			userDao.saveEvent(events, user);
		}
		
               userDao.saveEvent(events, user);
              
         return "redirect:/user/Display.html";
    }
    
    
    // Edit Guest
    @RequestMapping(value = "/user/editGuest.html", method = RequestMethod.GET)
    public String editGuest( @RequestParam Integer gid,ModelMap models )
    {
    	invitation i=userDao.getInvite(gid);
    	models.put("invite", i);
		return "editGuest";
    }

    @RequestMapping(value = "/user/editGuest.html", method = RequestMethod.POST)
    public String editGuest( @ModelAttribute("invite") invitation invite, BindingResult bindingResult,HttpSession http,
        SessionStatus sessionStatus )
    {
  	  userValidator.validateGuest( invite, bindingResult );
      if( bindingResult.hasErrors() ) return "editGuest";

    	Integer eid=(Integer) http.getAttribute("eeid");
    	System.out.println(eid);
    	System.out.println(invite.getInvite_id());
    	userDao.saveInvite1(invite,eid);
    	
         return "redirect:/user/Display.html";
    }
    
    
    //Delete Guest
    @RequestMapping(value = "/user/Delete.html", method = RequestMethod.GET)
    public String Delete( @RequestParam Integer gid,ModelMap models )
    {
    	invitation i=userDao.getInvite(gid);
    	userDao.delete(i);
    	//models.put("invite", i);
		return "redirect:/user/Display.html";
    }
    		    
}