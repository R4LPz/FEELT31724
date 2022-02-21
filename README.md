# 1. Objetivo

O  objetivo  deste  trabalho  é  a  implementação  de  um  servidor  Web  "multithreaded". Implementaremos  a  versão  1.0  do  HTTP,  definida  no  RFC  1945,  onde  mensagens  de pedido  HTTP  separadas  são  enviadas  para  cada  objeto  da  página  Web.Este  servidor deverá manipular múltiplas requisições simultâneas de serviçoem paralelo. Isso significa que  o  servidor  Web  é  "multithreaded".  Ele  deverá  ser  capaz  de  atender  pedidos  de transferência  de  arquivos  gerados  a  partir  de  um  browser  comum,  como  o  Firefox.  Os pedidos  serão  apresentados  emuma  porta  específica  e  o  servidor  Web  deverá  aceitar conexões não persistentes e simultâneas de um número arbitrário de clientes Web.

# 2. Introdução
O  servidor  Web  é  um  dos  componentes  da  aplicação  Web  e  se  baseia  num  programa servidor. Quando esse programa é executado, cria-se o processo servidor que se mantém na “escuta” pelos clientes Web que queiram extrair documentos usando o protocolo HTTP. Por meio de conexões TCP, ele recebe pedidos e envia respostas até que o cliente Web ou o próprio servidor Webdecida encerrar a conexão.

# 3. Desenvolvimento
A partir dos passos enumerados no arquivo "Tarefa de Programação 1: Construindo um servidor Web multithreaded", o arquivo "WebServer.java" foi construido. 

Este arquivo se trata de um código java que implementa um servidor web que opera a versão 1.0 do HTTP (RFC-1945). A operação do mesmo funciona de forma simples, implementamos uma classe principal que cria uma thread para ouvir uma porta fixa até que um cliente faça uma requisição TCP, nesse momento o sistema cria uma thread separada para aquela requisição e resolve esta conexão em uma porta distinta.

Como é um código simples, tem por função servir 4 rotas principais:

- http://localhost:3000/index.html
- http://localhost:3000/relatorio.pdf
- http://localhost:3000/matrix.gif
- http://localhost:3000/hackerman.jpg
- http://localhost:3000/notfound (ou qualquer outra rota que não esteja bem definida)

# 4. Testes
Para testar o servidor, como as chamadas são todas do tipo GET, podemos utilizar apenas um browser e nos conectarmos através de uma chamada local na porta 3000.

Para isso, primeiramente é necessário que executemos o servidor, para isso basta abrir um shell e executar o seguinte comando (é necessário ter uma versão do java instalada em sua máquina e executar este comando na pasta do projeto):

``` javac WebServer.java ```

Esse comando criará dois arquivos .class, o WebServer que é nossa classe principal e a classe de consumo chamada de HttpRequest. Com o procedimento realizado, basta que executemos o comando: 

``` java WebServer ```

Isso fará com que nosso método main da classe WebServer seja executado e crie a thread principal, a partir dai, qualquer requisição feita na porta 3000 por um cliente será operada pelo servidor, que por sua vez "logga" todas as requisições no console.

Com isso feito podemos começar a realizar nossos testes. Para isso basta abrir o browser de sua preferência e chamar as rotas citadas acima.

5. Conclusão
Esse trabalho permitiu melhor compreensão e análise de um servidor multithreaded, passo importante no desevolvimento dos estudantes da turma da disciplina.