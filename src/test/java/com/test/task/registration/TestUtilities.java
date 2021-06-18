package com.test.task.registration;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.RegistrationRequest;
import com.test.task.registration.entity.Status;
import lombok.experimental.UtilityClass;

import java.util.UUID;

import static com.test.task.registration.entity.Status.INITIAL;

/**
 * Не стоит в классах Test с анотацией @Before по причине того, что во всех тестах нужен этот блок кода
 */
@UtilityClass
public class TestUtilities {

	public void enrichValidRegistrationRequest(RegistrationRequest request) {

		request.setFirstName("Миша");
		request.setLastName("Богачев");
		request.setSecondName("Лорионтев");
		request.setLogin("SKB");
		request.setPassword("123456789");
		request.setEmailAddress("SKB@mail.ru");
	}

	public void enrichAccount(Account account) {
		account.setUuid(UUID.fromString("00000000-0000-0000-0000-000000000000"));
		account.setLogin("SKB_VIP");
		account.setFirstName("Миша");
		account.setLastName("Богачев");
		account.setSecondName("Лорионтев");
		account.setEmailAddress("SKB@mail.ru");
		account.setPassword("123456789");
		account.setAccountStatus(INITIAL);
	}
}
