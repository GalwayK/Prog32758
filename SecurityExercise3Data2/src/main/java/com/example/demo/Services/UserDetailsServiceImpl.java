package com.example.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Database.DatabaseAccess;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	@Autowired
	private DatabaseAccess dataAccess;

	public void setDatabaseAccess(DatabaseAccess dataAccess)
	{
		this.dataAccess = dataAccess;
	}
	
	@Override 
	public UserDetails loadUserByUsername(String username) 
		throws UsernameNotFoundException
	{
		//We will need to use a different User later, so do not import
		com.example.demo.Beans.User user = dataAccess.findUserAccount(username);
		if (user == null)
		{
			String strUserNotFound = String.format("User %s not found.", username);
			System.out.printf(strUserNotFound);
			throw new UsernameNotFoundException(strUserNotFound);
		}
		
		List<String> roleList = dataAccess.getRolesById(user.getUserid());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if (roleList != null)
		{
			for (String role : roleList)
			{
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		
		UserDetails userDetails = (UserDetails)(new User(user.getEmail(), 
			user.getEncryptedPassword(), grantList));
		return userDetails;
	}
}
