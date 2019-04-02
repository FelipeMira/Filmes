# language: pt
Funcionalidade: Chamada na api de diretor

  @teste_api_rest_assured
  Cenario: Client ira chamar a api GET do jsonplaceholder
    Dado Que quero testar o retorno do jsonplaceholder
    Quando O cliente passa o complemento da url "1"
	Entao Chamo a api rest e valido os retornos