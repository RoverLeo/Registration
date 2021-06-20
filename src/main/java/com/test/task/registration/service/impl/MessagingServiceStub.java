package com.test.task.registration.service.impl;

import com.test.task.registration.entity.MessageMap;
import com.test.task.registration.exception.TimeoutException;
import com.test.task.registration.service.MessagingService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class MessagingServiceStub implements MessagingService<Map<String, String>, MessageMap> {

	@Override
	public UUID send(final Map<String, String> body) {
		UUID uuid = UUID.randomUUID();
		MessageMap message = new MessageMap(uuid, body);

		log.info("Message sent with correlationId {}", uuid.toString());

		return uuid;
	}

	@Override
	public MessageMap receive(final UUID messageId) {
		if(shouldThrowTimeout()) {
			sleep();

			throw new TimeoutException("Timeout!");
		}

		if(shouldSleep()) {
			sleep();
		}

		// Заглушка
		Map<String, String> body = new HashMap<String, String>();
		body.put("result", "true");

		return new MessageMap(UUID.randomUUID(), body);
	}

	@Override
	public MessageMap doRequest(final Map<String, String> request) {
		final UUID messageId = send(request);

		return receive(messageId);
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