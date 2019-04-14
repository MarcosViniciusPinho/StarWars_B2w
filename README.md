# Desafio B2W - StarWars

Projeto foi construído na base do DDD, as seguintes camadas foram:<br />
- Application
- Domain
- Infrastructure<br />

Tecnologias utilizadas no projeto, foram as seguintes:<br />
- Spring Boot
- Spring Data
- Flyway
- RestTemplate
- JPA
- MySql
<br />

Para testar os end-points da API, importar a collection localizada em 
src/test/resources/Desafio - B2W.postman_collection.json para seu postman.<br />

Para poder executar o projeto é obrigatório você ter o maven instalado em sua 
máquina local ou ter o mesmo configurado em sua IDE e digitar dois comandos:
- clean spring-boot:run(Para rodar o projeto)
- flyway:clean flyway:migrate(Para excluir e criar a tabela do banco de dados)
<br />
Obs: Antes de rodar estes dois comandos se certifique que a base de dados já
foi criada.

