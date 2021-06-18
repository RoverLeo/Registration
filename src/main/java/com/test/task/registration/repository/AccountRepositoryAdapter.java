package com.test.task.registration.repository;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.Status;

import java.util.UUID;

public interface AccountRepositoryAdapter {

	void saveAccount(final Account account);

	void updateAccount(final UUID id, final Status status);

	void deleteById(final UUID id);
}
