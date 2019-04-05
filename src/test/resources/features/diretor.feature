# language: pt
@teste_diretor
Funcionalidade: Chamada na api de diretor
  @teste_api
  Cenario: Client ira chamar a api GET de diretor
    Dado Que quero testar o retorno do diretror
    Quando O cliente chama a api de diretor
	Entao Valido o codigo de retorno 200

  @teste_selenium
  Cenario: Procurar filme e diretor no google
    Dado Que eu quero testar uma pesquisa no google
    Quando Informo o parametro da pesquisa
    E Clico em Pesquisar
    Entao Valido quantos resultados foram obtidos
    E reseto o webDriver