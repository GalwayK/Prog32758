package com.example.demo;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.example.demo.Security.SecurityConfig;
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer
{
	public SecurityWebApplicationInitializer()
	{
		super(SecurityConfig.class);
	}
}