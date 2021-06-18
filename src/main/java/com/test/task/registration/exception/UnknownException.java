package com.test.task.registration.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ResponseStatus(value = INTERNAL_SERVER_ERROR, reason="Неизвестная ошибка")
public class UnknownException extends RuntimeException {

	public UnknownException(Throwable cause) {
		super(cause);
	}

}
