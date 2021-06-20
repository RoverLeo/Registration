package com.test.task.registration.service;

import com.test.task.registration.entity.EmailLetter;

/**
 * Ориентировочный интерфейс мейлера.
 */
public interface SendMailer {

	void sendMail (EmailLetter letter);

}

