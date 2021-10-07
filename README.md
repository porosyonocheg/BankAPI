##БанкAPI
###Требования
- JDK не ниже 8 версии
- База данных  H2

###Установка и запуск из командной строки
1) Копируем проект: git clone
2) mvn package - собираем, пройдя все тесты (требуется Maven)
3) java -jar BankApi-1.0-SNAPSHOT.jar

###Возможности
1) Получение баланса карты по номеру счёта : GET-запрос с номером аккаунта в качестве параметра cardNumber на http://localhost:8080//api/cards/balance?cardNumber=
2) Получение списка карт по номеру счёта: GET-запрос с номером cчёта в качестве параметра accountNumber на http://localhost:8080/api/cards/list?accountNumber=
3) Добавление новой карты (не оттестировано): POST-запрос с JSON-объектом вида {account:<номер аккаунта в виде строки>, number:<номер карты в виде строки>,type:<тип карты: кредитная, дебетовая и пр.>,payment:<платёжная система>} на http://localhost:8080/api/cards/new
4) Внесение средств (планируется)