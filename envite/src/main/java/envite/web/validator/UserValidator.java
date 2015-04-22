package envite.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import envite.model.Event;
import envite.model.invitation;
import envite.model.user;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports( Class<?> clazz )
    {
        // clazz is User.class or a subclass of User
        return user.class.isAssignableFrom( clazz );
    }
    
     
	@Override
	public void validate(Object target, Errors errors) 
	{
		user users = (user) target;

        if( !StringUtils.hasText( users.getF_name()) )
            errors.rejectValue( "f_name", "error.f_name.required" );

        if( !StringUtils.hasText( users.getL_name()) )
            errors.rejectValue( "l_name", "error.l_name.required" );
        if( !StringUtils.hasText( users.getUsername()) )
            errors.rejectValue( "username", "error.username.required" );
	
        if( !StringUtils.hasText( users.getPassword()) )
            errors.rejectValue( "pswrd", "error.pswrd.required" );
        if( !StringUtils.hasText( users.getUser_email()) )
            errors.rejectValue( "user_email", "error.user_email.required" );
  }


	public void validateLogin(Object target, Errors errors) 
	{
		user users = (user) target;
        if( !StringUtils.hasText( users.getUsername()) )
            errors.rejectValue( "username", "error.username.required" );
	
        if( !StringUtils.hasText( users.getPassword()) )
            errors.rejectValue( "password", "error.password.required" );
  }
	public void validateEvent(Object target, Errors errors) 
	{
		Event events = (Event) target;
        if( !StringUtils.hasText( events.getEvent_name()) )
            errors.rejectValue( "event_name", "error.event_name.required" );
	
        if( !StringUtils.hasText( events.getEvent_message()) )
            errors.rejectValue( "event_message", "error.event_message.required" );
  }
	
	public void validateGuest(Object target, Errors errors) 
	{
		invitation invite = (invitation) target;
        if( !StringUtils.hasText( invite.getGuestName()) )
            errors.rejectValue( "guestName", "error.guestName.required" );
	
        if( !StringUtils.hasText( invite.getGuestEmail()) )
            errors.rejectValue( "guestEmail", "error.guestEmail.required" );
  }
	
       
}
