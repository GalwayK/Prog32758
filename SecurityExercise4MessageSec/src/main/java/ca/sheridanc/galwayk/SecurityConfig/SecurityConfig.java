package ca.sheridanc.galwayk.SecurityConfig;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private LoggedAccessDeniedHandler loggedAccessDeniedHandler;
	
	public SecurityConfig(LoggedAccessDeniedHandler loggedAccessDeniedHandler)
	{
		this.loggedAccessDeniedHandler = loggedAccessDeniedHandler;
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
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("Reader").password("Reader").roles("READER")
			.and()
			.withUser("Writer").password("Writer").roles("WRITER", "READER");
	}
	
}
