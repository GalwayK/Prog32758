package SecurityExercise1;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import SecurityExercise1.Security.SecurityConfig;

public class SecurityWebApplicationInitializer 
	extends AbstractSecurityWebApplicationInitializer
{
	public SecurityWebApplicationInitializer()
	{
		super(SecurityConfig.class);
	}
	
}
