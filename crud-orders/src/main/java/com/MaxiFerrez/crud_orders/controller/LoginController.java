package com.MaxiFerrez.crud_orders.controller;

import com.MaxiFerrez.crud_orders.dto.User;
import com.MaxiFerrez.crud_orders.security.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

	@Autowired
	JWTAuthtenticationConfig jwtAuthtenticationConfig;

	// Simulación de usuarios hardcodeados
	private static final Map<String, String> USERS = new HashMap<>();
	static {
		USERS.put("maxi", "123456");
		USERS.put("user2", "password2");
	}

	@GetMapping("/login")
	public User loginGet(@RequestParam("user") String username, @RequestParam("encryptedPass") String encryptedPass) {
		// Verificación simple de usuario y contraseña
		if (USERS.containsKey(username) && USERS.get(username).equals(encryptedPass)) {
			String token = jwtAuthtenticationConfig.getJWTToken(username);
			return new User(username, encryptedPass, token);
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}

	@PostMapping("/api/login")
	public User login(@RequestParam("user") String username, @RequestParam("encryptedPass") String encryptedPass) {
		// Verificación simple de usuario y contraseña
		if (USERS.containsKey(username) && USERS.get(username).equals(encryptedPass)) {
			String token = jwtAuthtenticationConfig.getJWTToken(username);
			return new User(username, encryptedPass, token);
		} else {
			throw new RuntimeException("Invalid credentials");
		}
	}
}
