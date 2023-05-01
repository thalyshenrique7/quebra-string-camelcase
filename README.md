# Relatório TDD

### Projeto: Quebra de Strings com CamelCase em Java

Passos realizados:
1. Criei a classe TesteCamelCase, adicionei o método inicializa para instanciar o objeto
ConverterCamelCase antes de realizar os testes e declarei a variáveis de instância:

+ private ConverterCamelCase converter - Irá invocar os métodos.
+ List<?> lista - Lista de testes
  + ? -> Tipo genérico, espera qualquer tipo de dado

2. Criei a classe ConverterCamelCase com os métodos:
+ stringValida -> verifica se a resposta não é nula ou menor que 0
+ tamanho -> retorna o tamanho da String removendo os espaços vazios
+ contemNumeroNaString -> verifica se possuí número na String
+ iniciaComNumero -> verifica se a String inicia com número, caso sim, lança uma
Exception
+ contemCaracterEspecial -> verifica se a String inicia com caracteres especiais, caso
sim, lança uma Exception
+ compiladorString -> busca por sequência de letras maiúsculas, minúsculas e
números
+ converterLowerCase -> recebe a String e converte em letras minúsculas

3. Criei a Classe ConverterException, onde tem um construtor que recebe uma String para
retornar os erros ao usuário.

4. Na Classe TesteCamelCase realizo os seguintes testes:
+ testeException -> verifico se a chamada do ConverterException será bem sucedida
+ testeConverterCamelCase -> realizo os testes: “
  + String simples
  + camelCase
  + CamelCase
  + SIMPLES
  + SonsOfAnarchy
  + com Número
  + Tamanho da String
  + String Null
  + Inicia com Número
  + Caracter Especial
  + Número na String

> Autor: Thalys Henrique

https://www.linkedin.com/in/thalyshenrique7/
