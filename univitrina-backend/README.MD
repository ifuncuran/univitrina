# Api сервер

Перед запуском нужно поднять postgres  
`docker run --rm --name univitrina-postgres -d --env POSTGRES_PASSWORD=123 --env POSTGRES_USER=univitrina --env POSTGRES_DB=univitrina -p 5432:5432 postgres`

Запуск  
`mvn clean install exec:java`

`mvn clean install -Pdocker`

Проверка доступности сервиса  
`curl localhost:4000/status`
