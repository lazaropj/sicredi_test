## INSTRUÇÕES

Esse projeto foi desenvolido com Java(1.8) juntamente com Spring Boot, então para rodar o projeto
é necessário ter a JDK 8 instalado na sua máquina. É necessário também ter o ActiveMQ rodando na porta 61616 
(caso seja necessário trocar essa porta, não esqueça de alterar o endereço no arquivo de properties).

Dentre os frameworks e ferramentas usadss, estão:

* Spring Web
* Spring Data
* Hibernate
* Banco de dados H2
* Spring JMS (Client para conectar no ActiveMQ)
* Swagger
* IntelliJ IDEA

#### Bonus 1 

Foi feita a integração com a API de validação de CPF

#### Bonus 2

Foi feito a integração da aplicação com um broken de mensageria (ActiveMQ).

#### Documentação

A documentação de todos os Endpoint da aplicação estão no Swagger(http://localhost:8080/swagger-ui.html).
 
