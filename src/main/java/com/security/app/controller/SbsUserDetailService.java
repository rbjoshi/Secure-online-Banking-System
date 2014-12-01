package com.security.app.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.security.app.dao.RoleDao;
import com.security.app.dao.UserDao;
import com.security.app.model.Role;
import com.security.app.model.User;

//@Service("userDetailsService")
public class SbsUserDetailService implements UserDetailsService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	
	@Transactional
	public UserDetails loadUserByUsername(final String userName)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 final User user = userDao.getUserById(userName); 
		List<Role> roles = roleDao.getAllRoles();

		return new UserDetails() {
		
			
			public boolean isEnabled() {
				// TODO Auto-generated method stub
				return user.getEnabled();
			}
			
			public boolean isCredentialsNonExpired() {
				// TODO Auto-generated method stub
				return user.getCredentialsNonExpired();
			}
			
			public boolean isAccountNonLocked() {
				// TODO Auto-generated method stub
				return user.getAccountNonLocked();
			}
			
			public boolean isAccountNonExpired() {
				// TODO Auto-generated method stub
				return user.getAccountNonExpired();
			}
			
			public String getUsername() {
				// TODO Auto-generated method stub
				return user.getUserId();
			}
			
			public String getPassword() {
				// TODO Auto-generated method stub
				return user.getPassword();
			}
			

			public Collection<? extends GrantedAuthority> getAuthorities() {
				// TODO Auto-generated method stub
				List<SimpleGrantedAuthority> myGrantedAuthorities = new ArrayList<SimpleGrantedAuthority>();
				myGrantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
				//myGrantedAuthorities.add(new SimpleGrantedAuthority("admin"));
				//myGrantedAuthorities.add(new SimpleGrantedAuthority("emp"));
				//myGrantedAuthorities.add(new SimpleGrantedAuthority("customer"));
				//myGrantedAuthorities.add(new SimpleGrantedAuthority("merchant"));
	
				return myGrantedAuthorities;
				
			}
		};
	}

}

