package com.test.task.registration.constants;

import lombok.experimental.UtilityClass;

/* Потенциально можно будет вынести в отдельный сервис для изменения параметров
   без перекомпиляции проекта или отдельных классов */
@UtilityClass
public class EmailLetterConstants {

	public static final String SENDER_ADDRESS = "SKB@yandex.ru";

	// Вот тут ещё бы уникальный токен подставлять
	public static final String APPROVE_LINK = "https://SKB.ru/verify";
	public static final String MESSAGE_EMAIL_APPROVED = "Здравствуйте, %s. Ваш адресс одобрен. Перейдите по ссылке для завершения регистрации %s";
	public static final String MESSAGE_EMAIL_DECLINED = "Здравствуйте, %s. Ваш адресс не был одобрен.";

}
