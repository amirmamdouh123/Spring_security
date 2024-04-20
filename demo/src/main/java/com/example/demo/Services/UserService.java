package com.example.demo.Services;

import com.example.demo.Entities.UserApp;
import com.example.demo.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepo userRepo;
	PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserApp> user=userRepo.getByUsername(username);
		if(user.isPresent()){
			return user.get();
		}
		throw new UsernameNotFoundException("User not found");
	}

}
