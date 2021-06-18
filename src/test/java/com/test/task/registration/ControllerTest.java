package com.test.task.registration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.task.registration.controller.RegistrationController;
import com.test.task.registration.entity.RegistrationRequest;
import com.test.task.registration.repository.AccountRepository;
import com.test.task.registration.service.Registrar;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private AccountRepository accountRepository;

	@MockBean
	private Registrar registrar;

	@Autowired
	private MockMvc mockMvc;

	RegistrationRequest request = new RegistrationRequest();

	@BeforeEach
	public void setup() {
		TestUtilities.enrichValidRegistrationRequest(request);
	}

	// Мог бы использовать MethodSource c Person для проверки всех атрибутов, но названия тестов более ясно показывают,
	// что проверяется и что работает/не работает. В тестах самое главное быстро показать, что сломалось и где
	public static Stream<String> getEmails() {
		return Stream.of(
				"SKB.@mail.com",
				"SKB@.com.com",
				"S K B@.com",
				"SKB.com",
				"SKB@.com",
				"SKB..1997@mail.com",
				"SKB@mail@mail.com",
				"SKB@.com"
		);
	}

	@SneakyThrows
	@Test
	public void validRegistrationRequest() {
		//when
		mockMvc.perform(
				post("/registration")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
		);

		//then
		Mockito.verify(registrar, Mockito.times(1)).register(any(RegistrationRequest.class));
	}

	@SneakyThrows
	@Test
	public void eptyEmail() {
		//given
		request.setEmailAddress("");

		//when
		mockMvc.perform(
				post("/registration")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
		)
				//then
				.andExpect(status().is(400));
	}

	@SneakyThrows
	@ParameterizedTest
	@MethodSource("getEmails")
	public void invalidEmail(String email) {
		//given
		request.setEmailAddress(email);

		//when
		mockMvc.perform(
				post("/registration")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
		)
				//then
				.andExpect(status().is(400));
	}

	@SneakyThrows
	@Test
	public void eptyName() {
		//given
		request.setLastName("");

		//when
		mockMvc.perform(
				post("/registration")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
		)
				//then
				.andExpect(status().is(400));
	}

	@SneakyThrows
	@Test
	public void shortPassword() {
		//given
		request.setPassword("1234");

		//when
		mockMvc.perform(
				post("/registration")
						.content(objectMapper.writeValueAsString(request))
						.contentType(MediaType.APPLICATION_JSON)
		)
				//then
				.andExpect(status().is(400));
	}
}
