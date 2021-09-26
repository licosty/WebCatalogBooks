### Тестовое задание

Предлагается реализовать с помощью spring boot/spring data/spring mvc бэкенд-приложение 
в виде электронного каталога книг. 
Все функции должны быть доступны через REST-API. Должны быть следующие функции:
1) Создать запись о книге (поля типа – наименование, авторы, год издания, ISBN code и можно другое);
2) Обновить запись;
3) Удалить запись;
4) Запрос списка записей по критерию (например, по вхождению в наименование текста и автору). 
С опциональной сортировкой по какому-либо полю.

---
Использованные технологии:
- [Maven];
- [Spring Boot];
- [Spring Data JPA];
- [H2].

[java]:<https://www.oracle.com/ru/java/technologies/javase-downloads.html>
[maven]:<https://maven.apache.org/download.cgi>
[spring boot]:<https://www.baeldung.com/spring-with-maven>
[spring data jpa]:<https://www.baeldung.com/spring-with-maven>
[h2]:<https://mvnrepository.com/artifact/com.h2database/h2>