# Запуск проект

### 1. Запуск контейнера
`docker-compose up -d`

### 2. Запуск jar файла
`java -jar artifacts/app-deadline.jar "-P:jdbc.url=jdbc:mysql://localhost:3306/db" "-P:jdbc.user=user" "-P:jdbc.password=password"`

### 3. Подключение к БД
`docker-compose exec mysql mysql -u user -p db`
