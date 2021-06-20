package com.test.task.registration.entity;

import java.util.Map;
import java.util.UUID;

public class MessageMap extends Message {

	/**
	 * Тут храняться значения. Например решения по email
	 * Также значение, какой вердикт по этому сообщению
	 */
	private final Map<String, String> data;

	public MessageMap(UUID uuid, Map<String, String> data) {
		super(uuid);
		this.data = data;
	}

	public String getValueOrNull(String key) {
		return contiansKey(key) ? data.get(key) : null;
	}

	private boolean contiansKey(String key) {
		return data.containsKey(key);
	}
}
