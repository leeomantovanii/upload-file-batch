# upload-file-batch - BACK END

OBJETIVO
--------
Escrever uma aplicação Java para fazer upload de arquivo de log e popular o banco de dados,
uma interface para listar/consultar/pesquisar os logs, e uma ultima interface com formulário
para inserção manual de log. Implemente o back-end com (Spring ou JavaEE) e front-end (JSP
ou SPA) (front-end em Angular será considerado um diferencial).

### DETALHES DO BACK-END:
- Definir o modelo de dados no PostgreSQL;
- Definir serviços para a inserção em batch (usando o arquivo de logs fornecido,
usando JPA);
- Definir serviços para a inserção de logs manuais (CRUD);
- Implementar filtros ou pesquisas de logs;
- (BÔNUS) Testes automatizados;

### DETALHES DO FRONT-END:
- Tela para inserção de logs manuais (CRUD);
- Tela para inserção de logs usando o arquivo modelo;
- Tela para buscar logs feitos por um determinado IP e por um intervalo de tempo;
- (BÔNUS) Dashboard para exibir o número de requests feitos por um determinado IP,
por hora, user-agent (agregação);

### FORMATO DO LOG:
- Data, IP, Request, Status, User Agent (delimitado por aspas duplas);
- O delimitador do arquivo de log é o caracter pipe (|);
- Formato de data: "yyyy-MM-dd HH:mm:ss.SSS";

### Script da criação do banco e da tabela


```js

CREATE DATABASE LOG;

CREATE TABLE TB_LOG (
      ID VARCHAR(36) UNIQUE NOT NULL,
      DATA timestamp(3) with time zone NOT NULL,
      IP varchar(20) NOT NULL,
      REQUEST VARCHAR(20) NOT NULL,
      STATUS INT NOT NULL,
      USER_AGENT VARCHAR(200) NOT NULL,
      PRIMARY KEY (ID)
);

```

### DETALHES DA CONSTRUÇÃO PROJETO:
- Spring Boot;
- Spring Data JPA;
- Maven;
- Construido na Clean Arquitetura;
- OpenCSV para leitura do arquivo;
- JUnit 5 para os testes unitários;
- Perfil sandbox e banco H2 em memória para testes de integração;
- Podam para mock dos objetos;
- MapStruct para mapear os objetos;
- PostgreSQL;

Melhorias futuras:
- Documentação da API - Swagger;
- Testes de componentes com WebMvcTest;
- Paginação do retorno do serviço GET;
- Testes automatizados;
