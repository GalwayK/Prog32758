package com.example.demo.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.Services.UserDetailsServiceImpl;


@SuppressWarnings("deprecation")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	private LoggingAccessDeniedHandler accessDeniedHandler;
	private UserDetailsServiceImpl userDetailsService;
	private BCryptPasswordEncoder passwordEncoder;
	
	
	public SecurityConfig(LoggingAccessDeniedHandler accessDeniedHandler, 
		UserDetailsServiceImpl userDetailsService, 
		BCryptPasswordEncoder passwordEncoder)
	{
		this.accessDeniedHandler = accessDeniedHandler;
		this.userDetailsService = userDetailsService;
		this.passwordEncoder = passwordEncoder;
	}

	
	public void configure(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authorizeRequests()
			.antMatchers("/secure/**", "/exercise/**").hasAnyRole("USER", "GUEST")
			.and()
			.authorizeRequests()
			.antMatchers("/**", "/js/**", "/css/**", "/images/**", "/login/**", "/h2-console/**").permitAll()
			.and()
			.formLogin().loginPage("/login").defaultSuccessUrl("/secure", true)
			.and()
			.logout().invalidateHttpSession(true).clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) 
			.logoutSuccessUrl("/login?logout")
			.and()
			.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
		httpSecurity.csrf().disable();
		httpSecurity.headers().frameOptions().disable();
	}
	
	public void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}