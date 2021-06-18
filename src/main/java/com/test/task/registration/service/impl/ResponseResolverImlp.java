package com.test.task.registration.service.impl;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.MessageMap;
import com.test.task.registration.entity.Status;
import com.test.task.registration.repository.AccountRepository;
import com.test.task.registration.repository.AccountRepositoryAdapter;
import com.test.task.registration.service.ResponseResolver;
import com.test.task.registration.service.SendMailer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import static com.test.task.registration.constants.EmailLetterConstants.APPROVE_LINK;
import static com.test.task.registration.constants.EmailLetterConstants.MESSAGE_EMAIL_APPROVED;
import static com.test.task.registration.entity.Status.DELETED;
import static com.test.task.registration.entity.Status.REQUEST_SENT_TO_EMAIL;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ResponseResolverImlp implements ResponseResolver {

	private final AccountRepositoryAdapter accountRepository;
	private final SendMailer mailer;

	public void resolve(MessageMap message, Account account) {
		if ("true".equalsIgnoreCase(message.getValueOrNull("result"))) {
			String messageForLetter = String.format(MESSAGE_EMAIL_APPROVED, account.getFirstName(), APPROVE_LINK);

			mailer.sendMail(account.getEmailAddress(), messageForLetter);

			accountRepository.updateAccount(account.getUuid(), REQUEST_SENT_TO_EMAIL);
			account.setAccountStatus(REQUEST_SENT_TO_EMAIL);
		} else {
			accountRepository.deleteById(account.getUuid());
			account.setAccountStatus(DELETED);
		}
	}
}
