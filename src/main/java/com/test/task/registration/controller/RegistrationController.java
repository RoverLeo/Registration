package com.test.task.registration.controller;

import com.test.task.registration.entity.RegistrationRequest;
import com.test.task.registration.service.Registrar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;

@RestController
public class RegistrationController {

	@Autowired
	private Registrar registrar;

	@PostMapping("/registration")
	public ResponseEntity<String> registratePerson(@Valid @RequestBody RegistrationRequest registrationRequest) {

		return registrar.register(registrationRequest);
	}
}
