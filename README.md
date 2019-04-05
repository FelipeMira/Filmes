# Filmes

### API

Dentro da pasta 'main/java' está uma API produzida com Spring, MongoDB e documentação SpringFox.
Fiz tudo utilizando containers.

### Testes

Dentro da pasta 'main/test' estão os testes, adicionei um teste com Cucumber e TestRestTemplate.

### Build e Deploy no Play With Docker

Deixei tudo preparado para rodar os testes de api no https://labs.play-with-docker.com/.
É só entrar na url informada, fazer um git clone deste repositório e rodar o docker-compose up.

Quando rodar os containers os testes serão executados no Build e o Swagger da aplicação estará na porta 8086:

Deve ser substituido o @ no ssh pela porta 8086 seguido do ".", o ssh do inicio deve ser removido, ao final deve colocar o "/swagger-ui.html", formando a url:

ATENÇÃO EXEMPLO:
ssh ip172-18-0-19-bihks33c3o40009566v0@direct.labs.play-with-docker.com

http://ip172-18-0-19-bihks33c3o40009566v0-8086.direct.labs.play-with-docker.com/swagger-ui.html
