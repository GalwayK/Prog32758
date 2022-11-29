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
	@Autowired 
	private LoggingAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	public void configure(HttpSecurity httpSecurity) throws Exception
	{
		httpSecurity.authorizeRequests()
			.antMatchers("/secure/**", "/exercise/**").hasRole("USER")
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
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
}