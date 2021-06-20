package com.test.task.registration.service.impl;

import com.test.task.registration.entity.EmailLetter;
import com.test.task.registration.exception.TimeoutException;
import com.test.task.registration.service.SendMailer;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SendMailerStub implements SendMailer {

	@Override
	public void sendMail(EmailLetter letter) {
		if (shouldThrowTimeout()) {
			sleep();

			log.error("Timeout при отправки по адрессу {}, с сообщением {}.", letter.getToAddress(), letter.getMessageBody());
			throw new TimeoutException("Timeout при отправке письма");
		}

		if (shouldSleep()) {
			sleep();
		}

		// ok.
		log.info("Message sent to {}, body {}.", letter.getToAddress(), letter.getMessageBody());
	}

	@SneakyThrows
	private static void sleep() {
		Thread.sleep(TimeUnit.MINUTES.toMillis(1));
	}

	private static boolean shouldSleep() {
		return new Random().nextInt(10) == 1;
	}

	private static boolean shouldThrowTimeout() {
		return new Random().nextInt(10) == 1;
	}
}
