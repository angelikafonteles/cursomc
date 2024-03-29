package com.angelikafonteles.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.angelikafonteles.cursomc.domain.Cliente;
import com.angelikafonteles.cursomc.repositories.ClienteRepository;
import com.angelikafonteles.cursomc.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	
	@Autowired
	ClienteRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<Cliente> obj = repo.findByEmail(email);

		if (obj.isEmpty()) {
			throw new UsernameNotFoundException(email);
		}
		
		Cliente cli = obj.get();
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis());
	}

}
