package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired 
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	public void configure(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authorizeRequests()
			.antMatchers("/secure/**", "/exercise/**").hasRole("USER")
			.antMatchers("/**", "/js/**", "/css/**", "/images/**", "/login/**", "/h2-console/**").permitAll()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/secure", true)
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
			.logoutSuccessUrl("/login?logout")
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
			.withUser("kyle@fakeemail.com").password("KylePassword")
				.roles("USER", "GUEST")
			.and()
			.withUser("liam@fakeemail.com").password("LiamPassword")
				.roles("GUEST");
	}
}