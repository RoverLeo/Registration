package com.test.task.registration.service;

import com.test.task.registration.entity.RegistrationRequest;
import org.springframework.http.ResponseEntity;

public interface Registrar {

	ResponseEntity<String> register(RegistrationRequest registrationRequest);

}
