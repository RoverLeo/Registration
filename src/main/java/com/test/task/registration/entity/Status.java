package com.test.task.registration.entity;

/*Нам не нужен статус отправлен через message. Он не даёт никакой информации. В каком то смысле INITIAL заменяет его*/
public enum Status {
	/**
	 * начальный статус
	 */
	INITIAL,
	/**
	 * запрос на подтверждение регистрации отправлен на E-mail
	 */
	REQUEST_SENT_TO_EMAIL,
	/**
	 * конечный статус
	 */
	CONFIRMED,
	/**
	 * удалён
	 */
	DELETED
}
