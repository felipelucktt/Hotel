## Sumário
1. [Sobre o Projeto](#hotel)
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)
3. [Instalação Local](#instalação-local)
4. [Instalação Via Docker](#instalação-via-docker)
5. [Uso](#uso)
6. [Endpoints](#endpoints)
7. [Exemplos de Requisição](#exemplos-de-requisição)
8. [Configuração PostgreSQL](#configurando-o-postgres)

# Hotel
Este projeto é um sistema de gerenciamento de hotel com foco no cadastro de hóspedes e no controle de estadias, incluindo check-in e check-out. A aplicação realiza a cobrança com base no número de noites hospedadas, aplicando regras de negócio para cálculo de diárias e organização do fluxo de permanência no hotel.
## Tecnologias Utilizadas
- Java (21)
- Spring Boot
- JPA / Hibernate
- Maven
- API REST
- Database: Postgres
## Instalação Local
1. Clone o repositório utilizando o comando abaixo:
```bash
git@github.com:felipelucktt/Hotel.git
``` 
2. Instale as dependências do Maven
## Instalação via Docker
1.Clone o repositório utilizando o comando abaixo:
```bash
git@github.com:felipelucktt/Hotel.git
``` 

2. Caso esteja no Windows, necessário instalar o docker desktop

3. Gerar a imagem usando o comando: 
```bash
docker build -t hotel-api .
``` 

4. Subir os containers utilizando o comando:
```bash
docker-compose up
``` 
## Uso
1. Inicie a aplicação utilizando o Maven ou o Docker
2. A API será acessivel no endereço http://localhost:8080
## Endpoints
```
POST /guest - Endpoint responsável por realizar o cadastro de um hospede.

PUT /guest/{id} - Atualiza o cadastro de um hospede pelo id.

DELETE /guest/{id} - Deleta um hospede pelo ID

GET - /guest - Busca uma lista de hospedes. É possível adicionar query params para filtrar a busca. Query params disponíveis:

name - nome do hospede
document - documento do hospede
phoneNumber - número de telefone do hospede
inHotel - Filtro onde é possível inserir true or false para verificar se o hospede está ou não no hotel.

POST - /accommodation - Endpoint responsável por realizar o cadastro de uma hospedagem. É o endpoint utilizado para fazer o check-in.

PUT - /accommodation/checkout/{id} - Endpoint responsável por realizar o checkout da hospedagem.

```
## Exemplos de Requisição
- POST e PUT Guest
  ``` Json
  "name": "Rachel Luchetta",
  "document": "12345678901",
  "phoneNumber":"4798484890"
  ```
- POST Accommodation
    ``` Json
   "hasParkingSpot": true,
  "checkInDate": "2026-09-09T14:00:00",
  "guest": {
    "id": 3
	}
    ```
	
- PUT Accommodation
    ``` Json
   "checkOutDate": "2026-09-12T16:40:00"
    ```	
## Configurando o Postgres
Deve-se criar a base de dados no postgres seguindo os dados que estão alocados no arquivo application.properties ou modificar o arquivo para se adequar a base de dados que você já tem instalada. Por padrão o projeto está com a seguinte configuração:
```
database: hotel
port: 5433
username: plaza
password: 123456
```

Caso a aplicação seja utilizada em conjunto com o docker, já está incluso um container do PG Admin para consulta ao banco de dados. Para mais informações, olhar o arquivo: docker-compose.yml

## License
Esse projeto utiliza a licença do MIT. Mais detalhes estão contidos no arquivo [LICENSE](./LICENSE).
