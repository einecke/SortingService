# Serviço de triagem de livros

O serviço de triagem foi desenvolvido utilizando a linguagem de programação Java, consiste em um servlet que recebe um objeto do tipo JSON (JavaScript Object Notation)
através de um POST e devolve através da resposta desse POST, outro objeto JSON ordenado da maneira definida pelo arquivo de configuração config.xml.
Também foi desenvolvido (Inicialmente apenas para testes, mas achei interessante também enviar) uma pagina utilizando JavaScript, que envia uma requisição contendo alguns livros para o serviço de triagem e coloca a resposta em uma tabela.
> A Lista de livros deve ser enviada através do parâmetro “books”.

# Instruções de instalação
 - O serviço de triagem deve ser executado em um servidor web (Tomcat por exemplo);
 - A Ferramenta de envio de livros pode ser executada de qualquer navegador.

# Por que JSON?
Por utilizar o formato JSON, o serviço de triagem pode receber dados de diversos tipos de plataformas. 

# config.xml
```sh
<?xml version="1.0" encoding="UTF-8"?>
<config>
	<column>
		<name>year</name>
		<active>true</active>
		<order>3</order>
		<descending>false</descending>
	</column>
	<column>
		<name>author</name>
		<active>false</active>
		<order>1</order>
		<descending>false</descending>
	</column>
	<column>
		<name>title</name>
		<active>false</active>
		<order>2</order>
		<descending>true</descending>
	</column>
</config>
```

O arquivo config.xml contem os atributos que serão utilizados para a ordenação dos livros recebidos pelo serviço. Cada propriedade contem quatro atributos, sendo eles:

  - name: Nome do atributo a ser ordenado
  - active: Se o atributo vai ser considerado durante a ordenação (Deve receber true ou false)
  - order: Ordem do atributo na hora da ordenação, 
  - descending: Define se aquele atributo vai ser ordenado de forma descendente ou ascendente (Deve receber true ou false).
 
 > O caminho do config.xml é passado juntamente com a lista de livro através do parâmetro “configPath”.


# Algumas técnologias utilizadas

* [Google Guava] - Conjunto de classes utilitárias;
* [JDOM] - Manipulação de arquivos XML;
* [Log4j] - Gerenciamento de logs
* [Javascript/Html/Ajax] - Na ferramenta para envio de livros para o serviço.
* [JUnit] - Automação de testes
