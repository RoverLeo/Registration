package com.test.task.registration;

import com.test.task.registration.entity.Account;
import com.test.task.registration.exception.EmailDuplicateException;
import com.test.task.registration.exception.LoginDuplicateException;
import com.test.task.registration.repository.AccountRepository;
import com.test.task.registration.repository.AccountRepositoryAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@SpringBootTest
@AutoConfigureMockMvc
public class AccountSaveTest {

	@MockBean
	private AccountRepository accountRepository;

	@Autowired
	private AccountRepositoryAdapter accountRepositoryAdapter;

	private Account account = new Account();

	@BeforeEach
	public void setup() {
		//given
		TestUtilities.enrichAccount(account);
	}

	@Test
	public void accountSaveOkTest() {
		Mockito.when(accountRepository.findByEmailAddress(any(String.class))).thenReturn(Optional.empty());
		Mockito.when(accountRepository.findByLogin(any(String.class))).thenReturn(Optional.empty());

		//when
		accountRepositoryAdapter.saveAccount(account);

		//then
		Mockito.verify(accountRepository, times(1)).saveAndFlush(any(Account.class));
	}

	@Test
	public void accountSaveDuplicateEmailTest() {
		Mockito.when(accountRepository.findByEmailAddress(any(String.class))).thenReturn(Optional.of(new Account()));
		Mockito.when(accountRepository.findByLogin(any(String.class))).thenReturn(Optional.empty());

		assertThrows(EmailDuplicateException.class, () -> accountRepositoryAdapter.saveAccount(account));
	}

	@Test
	public void accountSaveDuplicateLoginTest() {
		Mockito.when(accountRepository.findByEmailAddress(any(String.class))).thenReturn(Optional.empty());
		Mockito.when(accountRepository.findByLogin(any(String.class))).thenReturn(Optional.of(new Account()));

		assertThrows(LoginDuplicateException.class, () -> accountRepositoryAdapter.saveAccount(account));
	}
}
