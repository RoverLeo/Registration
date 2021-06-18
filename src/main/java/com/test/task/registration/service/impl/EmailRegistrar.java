package com.test.task.registration.service.impl;

import com.test.task.registration.entity.*;
import com.test.task.registration.exception.EmailDuplicateException;
import com.test.task.registration.exception.LoginDuplicateException;
import com.test.task.registration.exception.RegistrationClientException;
import com.test.task.registration.exception.RegistrationServerException;
import com.test.task.registration.exception.TimeoutException;
import com.test.task.registration.exception.UnknownException;
import com.test.task.registration.repository.AccountRepositoryAdapter;
import com.test.task.registration.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.test.task.registration.entity.Status.DELETED;
import static org.springframework.http.HttpStatus.OK;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmailRegistrar implements Registrar {

	@Qualifier("accountEnricher")
	private final Enricher<Account, RegistrationRequest> enricherAccount;

	@Qualifier("messageEnricher")
	private final Enricher<Map<String, String>, Account> enricherMessage;

	private final SendMailer mailer;

	private final AccountRepositoryAdapter accountRepository;

	private final ResponseResolver resolver;

	private final MessagingService<Map<String, String>, Message> messagingService;

	public ResponseEntity<String> register(final RegistrationRequest request) {
		try {
			log.info("Start register email {}", request.getEmailAddress());

			Account account = new Account();
			enricherAccount.enrich(account, request);
			accountRepository.saveAccount(account);

			Map<String, String> messageBody = new HashMap<>();
			enricherMessage.enrich(messageBody, account);
			MessageMap message = (MessageMap) messagingService.doRequest(messageBody);

			resolver.resolve(message, account);

			return getResponse(account);
		} catch (EmailDuplicateException | LoginDuplicateException e) {
			log.error("Ошибка регистрации из-за запроса", e);
			throw new RegistrationClientException(e);
		} catch (TimeoutException e) {
			log.error("Ошибка регистрации из-за сбоя сервера", e);
			throw new RegistrationServerException(e);
		} catch (Exception e) {
			log.error("Неизвестная ошибка", e);
			throw new UnknownException(e);
		}
	}

	private ResponseEntity<String> getResponse(Account account) {
		log.debug("Формируем ответ со статусом 200");
		if (account.getAccountStatus() == DELETED) {
			return new ResponseEntity<>("К сожалению ваш email не подтверждён", OK);
		}

		return new ResponseEntity<>("Ожидайте на почте письмо", OK);
	}
}
