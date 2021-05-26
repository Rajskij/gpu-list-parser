# Приложение для подбора видеокарты

## Условия

Необходимо написать инструмент для выбора оптимальной видеокарты с целью Майнинг, 
опираясь на цену и на hashrate этой видеокарты, используя цены с hotline.ua
полностью автоматизированный инструмент.
GET /rating должен выдвать отсортированный список видеокарт
( обновление должно быть автоматическим, происходить раз в 20 минут, например)

### 1.Используемые технологии/фреймворки

* Java 8;
* Servlet, JSP, JSTL;
* JDBC;
* MySQL;
* HTML, CSS (Bootstrap), JavaScript;
* Git, Maven, Tomcat 8.

### 2.Установка

Предполагается, что ПО, указанное в п.1 уже установлено, настроено и готово к работе;
Клонировать репозитарий git clone https://github.com/Rajskij/gpu-list-parser;
Запустить MySQL сервер, создать там схему gpu;
Запустить Tomcat 8.5.65 сервер;

### 3.Запуск: перейти по полученной ссылке вида http://localhost:8080.
