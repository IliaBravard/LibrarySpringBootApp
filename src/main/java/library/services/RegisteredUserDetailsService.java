package library.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import library.beans.User;
import library.repositories.UserRepository;
import library.security.RegisteredUserDetails;

public class RegisteredUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository repo;

	@Override
	public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
		User toFind = repo.findByEmail(emailAddress);

		if (toFind == null) {
			throw new UsernameNotFoundException("The User Could Not Be Found!");
		}
		return new RegisteredUserDetails(toFind);
	}
}