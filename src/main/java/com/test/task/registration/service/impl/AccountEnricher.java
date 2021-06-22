package com.test.task.registration.service.impl;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.RegistrationRequest;
import com.test.task.registration.service.Enricher;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.test.task.registration.entity.Status.INITIAL;

@Component("accountEnricher")
public class AccountEnricher implements Enricher<Account, RegistrationRequest> {

	@Override
	public void enrich(final Account account, final RegistrationRequest source) {
		account.setUuid(UUID.randomUUID());
		account.setAccountStatus(INITIAL);

		// Чтобы адресса skb@ya.ru sKb@ya.ru SKB@ya.ru не считать разными
		account.setEmailAddress(source.getEmailAddress().toLowerCase());
		account.setLogin(source.getLogin());
		account.setFirstName(source.getFirstName());
		account.setSecondName(source.getSecondName());
		account.setLastName(source.getLastName());
		account.setPassword(source.getPassword());
	}
}
