# NSFC - Airlines


O projeto NSFC-Airlines consiste em uma aplicação web com uma página que, ao ser acessada, lista todos os vôos da companhia NSFC-Airlines e suas informações, tais como código do vôo, status, aeroportos, cidades, estados e países de saída e chegada. Possui opção de filtro dos vôos por status, aeroportos e intervalos de chegada e saída. Ao clicar no código de um vôo exibido na tabela de vôos é aberto um modal com mais informações, como: nome do piloto, inscrição e modelo do avião.

# Ferramentas

Para a construção do projeto foram utilizadas as seguintes tecnologias:

* Java 1.7.0_80 

* Hibernate 4.3.9.Final

* Spring MVC 4.3.2.Final

* Junit 4.12

* Javascript 

* AngularJS 1.5.8

* Angular-ui-bootstrap 2.1.3

* HTML5

* CSS3

* Maven 3.0.5

* Tomcat7

* Jackson 2.5.0

* Postgresql 9.1-901

* Liquibase 3.0.5

* Hsqldb 2.3.1

# Arquitetura

O projeto foi dividido em 4 módulos maven: nsfc-airlines,  nsfc-airlines-core,  nsfc-airlines-web,  nsfc-airlines-liquibase.

O módulo pai é nsfc-airlines.

O módulo nsfc-airlines-web é um módulo filho e possui todos os recursos pertencentes ao front-end da aplicação.

O módulo nsfc-airlines-core é um módulo filho e tem os serviços e entidades pertencentes ao back-end da aplicação.

O módulo nsfc-airlines-core-liquibase é um módulo filho e possui os scripts de geração do banco de dados.

O projeto realiza comunicação do tipo REST (Representational State Transfer), ou seja, o servidor não guarda estados do cliente. 

Url da página inicial do projeto: http://8080/nsfc-airlines/route

Url para obter todos os vôos da companhia: http://localhost:8080/nsfc-airlines/flights

Url para obter todos os aeroportos: http://localhost:8080/nsfc-airlines/airports

Url para obter todos os status: http://localhost:8080/nsfc-airlines/status

O front-end da aplicação foi feito com html5, css3, javascript, angularJS e bootstrap.
Já o back-end foi construído  com Java e algumas frameworks, tais como: Hibernate, Spring MVC, Jackson, Liquibase, JUNIT.

O servidor utilizado para realizar o deploy da aplicação é o tomcat.

A automação da build do projeto, assim como seu gerenciamento de dependências foram realizados pelo maven.

O banco de dados utilizado para subir a aplicação é o Postgresql e para subir os testes é o banco em memória HSQLDB.

A criação das tabelas do banco de dados foi feita com liquibase.

A framework Jackson foi utilizada para converter objetos do tipo JSON em objetos Java e vice-versa.

JUnit foi utilizada para realização de testes unitários.

Hibernate foi utilizada para implementação da JPA.

Spring MVC foi utilizado para mapeamento das urls para os controladores e injeção de dependências.

# Entidades

As entidades do projeto são:

* Flight

* FlighInfo

* Airport

* Airplane

# Controladores

Os controladores do projeto são:

* FlightsController
* RouteController
* AirportsController
* StatusController

# Serviços

* FlightService
* AirportService
