package com.test.task.registration.service;

import com.test.task.registration.entity.Message;

import java.util.UUID;

/**
 * Ориентировочный интерфейс нашего messaging решения.
 */
public interface MessagingService<T, A extends Message> {

	/**
	 * Отправка сообщения в шину.
	 *
	 * @param body сообщение для отправки.
	 *
	 * @return идентификатор отправленного сообщения (correlationId)
	 */
	UUID send(final T body);

	/**
	 * Встает на ожидание ответа по сообщению с messageId.
	 *
	 * Редко, но может кинуть исключение по таймауту.
	 *
	 * @param messageId идентификатор сообщения, на которое ждем ответ.
	 * @return Тело ответа.
	 */
	A receive(final UUID messageId);

	/**
	 * Отправляем сообщение и ждем на него ответ.
	 *
	 * @param request тело запроса.
	 * @return тело ответа.
	 */
	A doRequest(final T request);
}
