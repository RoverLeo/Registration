package com.test.task.registration.repository.impl;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.Status;
import com.test.task.registration.exception.EmailDuplicateException;
import com.test.task.registration.exception.LoginDuplicateException;
import com.test.task.registration.repository.AccountRepository;
import com.test.task.registration.repository.AccountRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import static com.test.task.registration.constants.ExceptionTextConstants.EMAIL_DUPLICATE_TEXT;
import static com.test.task.registration.constants.ExceptionTextConstants.LOGIN_DUPLICATE_TEXT;
import static java.lang.Boolean.TRUE;

@Slf4j
@Component
@RequiredArgsConstructor()
public class AccountRepositoryAdapterImpl implements AccountRepositoryAdapter {

	private final AccountRepository accountRepository;

	@Override
	public void saveAccount(final Account account) {
		if (absentDuplicate(account)) {
			log.debug("сохраняем аккаунт в бд");
			accountRepository.saveAndFlush(account);
		}
	}

	@Override
	public void updateAccount(final UUID id, final Status status) {
		log.debug("обновляем статус аккаунта в бд");
		accountRepository.updateStatus(id, status);
	}

	@Override
	public void deleteById(UUID id) {
		log.debug("проверяем есть ли аккаунт в базе");
		if (accountRepository.existsById(id)) {
			log.debug("аккаунт найден - удаляем его из бд");
			accountRepository.deleteById(id);
		}
	}

	private boolean absentDuplicate(final Account account) {
		log.error("Ищем account с дубликатом email в бд");
		Optional<Account> emailDuplicate = accountRepository.findByEmailAddress(account.getEmailAddress());
		if (emailDuplicate.isPresent()) {
			log.error("Аккаунт с email {} уже есть", emailDuplicate.get());
			throw new EmailDuplicateException(EMAIL_DUPLICATE_TEXT);
		}

		log.error("Ищем account с дубликатом login в бд");
		Optional<Account> loginDuplicate = accountRepository.findByLogin(account.getLogin());
		if (loginDuplicate.isPresent()) {
			log.error("Аккаунт с login {} уже есть", loginDuplicate.get());
			throw new LoginDuplicateException(LOGIN_DUPLICATE_TEXT);
		}

		return TRUE;
	}
}
