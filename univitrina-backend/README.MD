# Api сервер

Перед запуском нужно поднять postgres  
`docker run --rm --name univitrina-postgres -d --env POSTGRES_PASSWORD=123 --env POSTGRES_USER=univitrina --env POSTGRES_DB=univitrina -p 5432:5432 postgres`

Запуск  
`mvn clean install exec:java`

Проверка доступности сервиса  
`curl localhost:4000/status`

### Для работы с docker-compose
1. Запустить в /univitrina/univitrina-backend  
   `mvn clean install -Pdocker`

   1.1. если проблема с Checkstyle, запустить  
   `mvn clean install -Pdocker -Dcheckstyle.skip`

   1.2. если проблема с тестами, запустить  
   `mvn clean install -Pdocker -DskipTests`

2. В /univitrina запустить  
   `docker-compose up -d`
