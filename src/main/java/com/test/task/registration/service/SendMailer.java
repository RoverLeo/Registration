package com.test.task.registration.service;

/**
 * Ориентировочный интерфейс мейлера.
 */
public interface SendMailer {

	void sendMail (String toAddress, String messageBody);

}

