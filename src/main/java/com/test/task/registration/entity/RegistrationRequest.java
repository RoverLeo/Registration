package com.test.task.registration.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationRequest {

	@NotBlank(message = "Логин не заполнен")
	private String login;

	@NotBlank(message = "Пароль не соответсвует требованиям")
	@Size(min = 6)
	private String password;

	// НЕ ВСЕ ПРАВИЛА УЧТЕНЫ. ПРЕДПОЛОГАЕТСЯ ЧТО ВНЕШНЯЯ СИСТЕМА ПО ОДОБРЕНИЮ EMAIL ИМЕЕТ ВСЕ ПРАВИЛА
	@Email(message = "Не является Email адресом")
	@NotBlank
	private String emailAddress;

	@NotBlank(message = "Имя не заполнено")
	private String firstName;

	@NotBlank(message = "Фамилимя не заполнена")
	private String lastName;

	@NotBlank(message = "Отчество не заполнено")
	private String secondName;

}
