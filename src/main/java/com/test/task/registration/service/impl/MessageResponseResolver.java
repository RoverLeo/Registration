package com.test.task.registration.service.impl;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.EmailLetter;
import com.test.task.registration.entity.MessageMap;
import com.test.task.registration.repository.AccountRepositoryAdapter;
import com.test.task.registration.service.ResponseResolver;
import com.test.task.registration.service.SendMailer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.test.task.registration.entity.Status.DELETED;
import static com.test.task.registration.entity.Status.REQUEST_SENT_TO_EMAIL;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageResponseResolver implements ResponseResolver {

	private final AccountRepositoryAdapter accountRepository;
	private final SendMailer mailer;

	public void resolve(MessageMap message, Account account) {
		EmailLetter letter;

		if ("true".equalsIgnoreCase(message.getValueOrNull("result"))) {
			letter = EmailLetter.formApprovedLetter(account);
			mailer.sendMail(letter);

			accountRepository.updateAccount(account.getUuid(), REQUEST_SENT_TO_EMAIL);
			account.setAccountStatus(REQUEST_SENT_TO_EMAIL);
		} else {
			letter = EmailLetter.formDeclinedLetter(account);
			mailer.sendMail(letter);

			accountRepository.deleteById(account.getUuid());
			account.setAccountStatus(DELETED);
		}
	}
}