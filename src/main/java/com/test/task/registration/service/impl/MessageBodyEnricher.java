package com.test.task.registration.service.impl;

import com.test.task.registration.entity.Account;
import com.test.task.registration.service.Enricher;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("messageEnricher")
public class MessageBodyEnricher implements Enricher<Map<String, String>, Account> {

	@Override
	public void enrich(final Map<String, String> body, final Account source) {
		// Здесь может лежать и другая дополнительная информация.
		body.put("email", source.getEmailAddress());
	}
}
