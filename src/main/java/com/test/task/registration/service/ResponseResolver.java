package com.test.task.registration.service;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.MessageMap;

public interface ResponseResolver {

	void resolve(MessageMap message, Account account);

}
