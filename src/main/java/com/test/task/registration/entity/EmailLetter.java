package com.test.task.registration.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.test.task.registration.constants.EmailLetterConstants.*;

/* Не наследуем от Message - разные сущности, нужные для разных сервисов */

@Getter
@AllArgsConstructor
public class EmailLetter {

	private final String fromAddress;
	private final String toAddress;
	private final String messageBody;

	public static EmailLetter formApprovedLetter(Account account) {
		String bodyLetter = String.format(MESSAGE_EMAIL_APPROVED, account.getFirstName(), APPROVE_LINK);
		return new EmailLetter(SENDER_ADDRESS, account.getEmailAddress(), bodyLetter);
	}

	public static EmailLetter formDeclinedLetter(Account account) {
		String bodyLetter = String.format(MESSAGE_EMAIL_DECLINED, account.getFirstName());
		return new EmailLetter(SENDER_ADDRESS, account.getEmailAddress(), bodyLetter);
	}
}
