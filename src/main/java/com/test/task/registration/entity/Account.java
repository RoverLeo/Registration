package com.test.task.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.UUID;

import static javax.persistence.EnumType.*;
import static org.hibernate.annotations.CascadeType.DELETE;

/**
 * Гланвая сущность. Цель сервиса - зарегестрировать пользователя = положить в базу
 * Легче сразу работать с этой сушностью, а не делать сущность для сервиса регистрации и для
 * базы отдельные
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Account {

	/* Вводим UUID и будет использовать его как ключ. Long быстро закончиться, а у нас банк с миллионами людей, а данный
	сервис может испольховаться для нескольких сервисов компании */
	@Id
	@Cascade(DELETE)
	// todo тут вот связь
	private UUID uuid;

	private String login;

	/**
	 * Имя
	 */
	@Column(name = "first_name")
	private String firstName;

	/**
	 * Фамилия
	 */
	@Column(name = "last_name")
	private String lastName;

	/**
	 * Отчество или второе имя у иностранцев
	 */
	@Column(name = "second_name")
	private String secondName;

	/**
	 * Адресс электронной почты
	 */
	@Column(name = "email_address")
	private String emailAddress;

	private String password;

	/**
	 * Статус аккаунта
	 */

	/**
	 * Статус аккаунта
	 */
	@Column(name = "account_status")
	@Enumerated(STRING)
	private Status accountStatus;
}
