package in.mustaq.expensetrackerapi.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.mustaq.expensetrackerapi.entity.User;
import in.mustaq.expensetrackerapi.repository.UserRepository;

@Service
public class CustomUserDetialsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User existingUser = userRepository.findByEmail(email).orElseThrow
				(() -> new UsernameNotFoundException("user not found for the email : "+email));
		
		return new org.springframework.security.core.userdetails.User(existingUser.getEmail(),existingUser.getPassword(),new ArrayList<>());
	}

}
