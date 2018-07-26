Meta:

- Escrever um parser que acesse um arquivo de LOG, carregue os dados em um banco de dados e valide se um determinado IP fez mais que um determinado número de requisiçõe em um período de tempo.

 

Java (SPRINGBOOT)

----

 (1) Criar uma aplicação que carregue um aarquivo de LOG em um banco de dados. O delimitador do arquivo é um pipe (|)

 

(2) O APP precida dos campos "startDate", "duration" e "threshold" como argumentos da linha de comando.

- "startDate" está no formato "yyyy-MM-dd.HH:mm:ss" ,

- "duration" pode ser apenas de dois tipos: "hourly", "daily"

- "threshold" de ser um integer.

 

(3) Funcionamento:

 

    java -jar parser.jar --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

               

O app deve encontrar todos os IPs que fizeram mais que 100 requisições entre 2017-01-01.13:00:00 e 2017-01-01.14:00:00 (uma hora) e imprimí-lo no console como uma informação. Também deve carregar estes dados para uma tabela de banco de dados com o comentário do porquê está bloqueado.

 

    java -jar "parser.jar" --startDate=2017-01-01.13:00:00 --duration=daily --threshold=250

 

O app deve encontrar todos os IPs que fizeram mais que 250 requisições entre 2017-01-01.13:00:00 a 2017-01-02.13:00:00 (24 horas) e imprimí-lo no console como uma informação. Também deve carregar estes dados para uma tabela de banco de dados com o comentário do porquê está bloqueado.

 

 

JPA

---

 

(1) Pesquisar todos os IP's que fizeram mais de uma quantidade de requisiçõe em um dado período.

 

    Ex: Pesquisar IPs que fizeram mais de 100 requisições entre 2017-01-01.13:00:00 e 2017-01-01.14:00:00.

 

(2) Pesquisar todas as requisições de um determinado IP.

              

 

LOG (Formato)

----------

Date, IP, Request, Status, User Agent (delimitado por PIPE - veja o arquivo exemplo em anexo)

 

Onde:

Date: "yyyy-MM-dd HH:mm:ss.SSS"

 

Ao rodar o app com o arquivo em anexo:

- The arquivo tem 200 "hourly" e 500 "daily" como limitações, ou seja:

 

(1)

Ao rodar:

 

    java -jar parser.jar --startDate=2017-01-01.15:00:00 --duration=hourly --threshold=200

O resultao deverá ser 192.168.11.231.

No arquivo, o IP 192.168.11.231 tem 200 ou mais requisições entre 2017-01-01.15:00:00 e 2017-01-01.15:59:59

 

(2)

Ao rodar:

 

    java -jar parser.jar --startDate=2017-01-01.00:00:00 --duration=daily --threshold=500

 

O resultado deverá ser 192.168.102.136.

No arquivo, o IP 192.168.102.136 tem 500 ou mais requisições entre 2017-01-01.00:00:00 e 2017-01-01.23:59:59

 

 

Entrega

-------

 

(1) O APP em Spring Boot deve rodar por linha de comando

               

    java -jar parser.jar --accesslog=/path/to/file --startDate=2017-01-01.13:00:00 --duration=hourly --threshold=100

 

(2) Código fonte

 

(3) Testes de Integração
