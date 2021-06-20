Тестовое задание.

Что упрощено/сделано абстрактно/не сделано:
Конфиги для подключения к бд
message система и сами сообщения
Нет проверки на sql инъекции. Предпологается что есть шина, которая проверяет приходящие сообщения на это


Что можно было бы сделать/улучшить:
Сделать часть с подтверждением email после перехода по ссылке на почте
Формировать уникальный токен для подтверждения

Задание:
Форма регистрации с отправкой е-мейла после одобрения внешней системой.
Нужно реализовать форму регистрации в приложении, в которой необходимо заполнить:
- логин,
- пароль,
- адрес электронной почты,
- ФИО.
  После отправки формы в наш бэкенд, мы регистрируем данные из нее в нашей БД, а также
  отправляем
  ее для одобрения во внешней системе.
  Пусть обмен с этой внешней системой будет через некое messaging решение. После одобрения
  или
  отклонения заявки, наше приложение должно отправить сообщение на электронную почту
  нашему
  пользователю с результатом проверки.
  Стэк: Java 8+, Spring boot 2.0, dbms - h2. Для тестов предпочтение Junit/Mockito/Assertj, т.к. на
  проекте будут именно они. Остальное по вкусу.
  В качестве абстракции над шиной и почтовым сервисом предлагаю взять такой набросок:
  https://pastebin.com/CaJVHykc
  Возвращать из них можно заглушки, дабы сэкономить время на реализацию тестового задания.
  При реализации следует помнить, что в реальной эксплуатации любая часть нашей системы
  может отказать.
  Будем очень рады обоснованиям принятых архитектурных решений. Комментарии в коде к ним
  крайне приветствуются.