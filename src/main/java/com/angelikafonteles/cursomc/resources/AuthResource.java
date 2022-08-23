package com.angelikafonteles.cursomc.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.angelikafonteles.cursomc.security.JWTUtil;
import com.angelikafonteles.cursomc.security.UserSS;
import com.angelikafonteles.cursomc.services.UserService;

@Controller
@RequestMapping(value="/auth")
public class AuthResource {

	@Autowired
	private JWTUtil jwtUtil;
	
	@PostMapping(value="/refresh_token")
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
	UserSS user = UserService.authenticated();
	String token = jwtUtil.generateToken(user.getUsername());
	response.addHeader("Authorization", "Bearer " + token);
	return ResponseEntity.noContent().build();
	}
}
