package com.test.task.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(value = BAD_REQUEST, reason="Ошибка запроса клиента")
public class RegistrationClientException extends RuntimeException {

	public RegistrationClientException(Throwable cause) {
		super(cause);
	}

}
