# Events Manager API

Esta é uma API desenvolvida em Java com Spring Boot para gerenciamento de eventos, permitindo o cadastro de usuários, inscrições e eventos. Além disso, possibilita a visualização do ranking de indicações geral e por usuário.

## Tecnologias Utilizadas
- Java 23
- Spring Boot 3.4.2
- Spring Data JPA
- Spring Web
- MySQL
- Maven
- Swagger para documentação da API

## Estrutura do Projeto
A estrutura do projeto segue a organização MVC com pacotes bem definidos:

```
src/main/java/br.com.nlw.events/
│-- controller      # Camada de controle (Endpoints)
│-- dto             # Data Transfer Objects (DTOs)
│-- exception       # Exceções personalizadas
│-- model           # Entidades do banco de dados
│-- repository      # Repositórios (Camada de persistência)
│-- service         # Regras de negócio e serviços
│-- EventsApplication.java  # Classe principal para inicialização
```

## Configuração e Execução

1. Clone o repositório:
```sh
 git clone https://github.com/eduardo-toste/EventsManager.git
```

2. Acesse o diretório do projeto:
```sh
 cd EventsManager
```

3. Compile o projeto com Maven:
```sh
 mvn clean install
```

4. Execute a aplicação:
```sh
 mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

## Funcionalidades

### Eventos
- `POST /events` - Criar um evento
- `GET /events` - Buscar todos os eventos
- `GET /events/{prettyName}` - Buscar um evento pelo seu Pretty Name

### Inscrições
- `POST /subscription/{prettyName}` - Inscrever-se em um evento
- `POST /subscription/{prettyName}/{userId}` - Inscrever-se em um evento com indicação de outro usuário

### Rankings
- `GET /subscription/{prettyName}/ranking` - Obter o ranking geral de indicações por evento
- `GET /subscription/{prettyName}/ranking/{userId}` - Obter o ranking por evento e usuário

## Documentação com Swagger
Esta API utiliza o Swagger para documentação interativa. Para acessar a documentação, inicie a aplicação e acesse:

```
http://localhost:8080/swagger-ui.html
```

## Banco de Dados
Por padrão, a API utiliza o banco de dados MySQL. Caso queira usar outro banco, altere as configurações no arquivo `application.properties`.

## Como Contribuir

Se você deseja contribuir com este projeto, siga os passos abaixo:

1. Faça um fork do repositório.
2. Crie uma nova branch:
   ```sh
   git checkout -b minha-feature
   ```
3. Faça suas alterações e commit:
   ```sh
   git commit -m "Minha nova feature"
   ```
4. Envie para o seu fork:
   ```sh
   git push origin minha-feature
   ```
5. Abra um Pull Request para revisão.