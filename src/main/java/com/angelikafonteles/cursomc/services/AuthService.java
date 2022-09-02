package com.angelikafonteles.cursomc.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.angelikafonteles.cursomc.domain.Cliente;
import com.angelikafonteles.cursomc.repositories.ClienteRepository;
import com.angelikafonteles.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository repo;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();

	public void sendNewPassword(String email) {

		Optional <Cliente> aux = repo.findByEmail(email);
		if (aux.isEmpty()) {
			new ObjectNotFoundException(
					"Objeto não encontrado! Email: " + email + ", Tipo: " + Cliente.class.getName());
		}
		
		Cliente cliente = aux.get();
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		repo.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for(int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) { //gera um digito
			return (char) (random.nextInt(10) + 48);
		} else if(opt == 1) { //gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
		} else { //gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}
}
