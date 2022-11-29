package ca.sheridanc.galwayk.SecurityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import ca.sheridanc.galwayk.Services.UserDetailsServiceImplementation;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private LoggedAccessDeniedHandler loggedAccessDeniedHandler;
	private UserDetailsServiceImplementation userDetailsServiceImplementation;
	private BCryptPasswordEncoder passwordEncoder;
	
	public SecurityConfig(LoggedAccessDeniedHandler loggedAccessDeniedHandler, 
		UserDetailsServiceImplementation userDetailsServiceImplementation, 
		BCryptPasswordEncoder passwordEncoder)
	{
		this.userDetailsServiceImplementation = userDetailsServiceImplementation;
		this.loggedAccessDeniedHandler = loggedAccessDeniedHandler;
		this.passwordEncoder = passwordEncoder;
	}
	
	public void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests()
			.antMatchers("/messageBoard/**").hasRole("READER")	
			.antMatchers("/messagePost/**").hasRole("WRITER")
			.antMatchers("/**", "/h2-console/**").permitAll()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/messageBoard")
			.and()
			.exceptionHandling().accessDeniedHandler(loggedAccessDeniedHandler)
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login?logout");
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth.userDetailsService(userDetailsServiceImplementation).passwordEncoder(passwordEncoder);
	}
}
