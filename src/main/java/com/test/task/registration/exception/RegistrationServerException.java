package com.test.task.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason="Ошибка на сервере")
public class RegistrationServerException extends RuntimeException {

	public RegistrationServerException(Throwable cause) {
		super(cause);
	}

}
