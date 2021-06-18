package com.test.task.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

/* Не наследуем от Message - разные сущности, нужные для разных сервисов */

@Getter
@AllArgsConstructor
public class EmailLetter {

	private String fromAddress;
	private String toAddress;
	private String messageBody;

}
