package com.test.task.registration.entity;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

public class MessageMap extends Message {

	/**
	 * Тут храняться значения. Например UUID сообщения в ответ на который пришло это сообщение
	 * Также значение, какой вердикт по этому сообщению
	 */
	private final Map<String, String> data;

	public MessageMap(UUID id, Map<String, String> data) {
		super(id);
		this.data = Collections.unmodifiableMap(data);
	}

	public String getValueOrNull(String key) {
		return contiansKey(key) ? data.get(key) : null;
	}

	private boolean contiansKey(String key) {
		return data.containsKey(key);
	}
}
