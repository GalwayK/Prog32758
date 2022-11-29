package ca.sheridanc.galwayk.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridanc.galwayk.Beans.UserAccount;
import ca.sheridanc.galwayk.Database.DataAccess;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService
{
	private DataAccess dataAccess;
	
	public UserDetailsServiceImplementation(DataAccess dataAccess) 
		throws UsernameNotFoundException
	{
		this.dataAccess = dataAccess;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		UserAccount loggedUser = dataAccess.findUserAccount(username);
		
		if (loggedUser == null)
		{
			String errorString = String.format("User %s not found\n.", username);
			System.out.printf(errorString);
			throw new UsernameNotFoundException(errorString);
		}
		List<String> roleList = dataAccess.getRolesById(loggedUser.getAccountId());
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
		
		if (roleList != null) 
		{
			for (String role : roleList)
			{
				grantList.add(new SimpleGrantedAuthority(role));
			}
		}
		UserDetails userDetails = (UserDetails)(new User(loggedUser.getUsername(), 
			loggedUser.getPassword(), grantList));
		return userDetails;
	}

}
