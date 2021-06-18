package com.test.task.registration.repository;

import com.test.task.registration.entity.Account;
import com.test.task.registration.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

	Optional<Account> findByEmailAddress(String email);

	Optional<Account> findByLogin(String login);

	@Query("update Account a set a.accountStatus = :account_status WHERE a.uuid = :uuid")
	void updateStatus(@Param("uuid") UUID id, @Param("account_status") Status status);
}
