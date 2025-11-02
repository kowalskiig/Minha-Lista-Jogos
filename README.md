#  Minha lista de Jogos

## Objetivo do projeto

- Sistema em que usuários podem criar gerenciar listas de jogos, realizando operações nas mesmas.
- O objetivo do projeto foi construir uma aplicação obtendo prática no desenvolvimento com Java e Spring Boot, e treinar habilidades que eu aprendo em treinamentos de forma prática, resolvendo um problema meu.
- Como esse projeto é antigo de Abril de 2025, fui aprendendo novas coisas e aplicando para deixar o projeto bem robusto e seguro.


## Etapas que o projeto passou

### 1 parte:

O projeto foi originalmente desenvolvido como um exercício introdutório para praticar:
- CRUD simples com **Java + Spring Boot**
- Relacionamentos básicos (`Game`, `GameList`, `BelongsTo`)
- Deploy com **Railway** para exposição pública da API

No entanto, não havia:
- Tratamento de exceções estruturado
- Validações adequadas dos dados
- Endpoins retornava entidades, e não retornavam status code.
- Também alguns problemas de performance com o banco de dados, com consultas ineficientes retornando todos os dados sempre.

### 2 parte:

Foco foi implementar e aplicar boas práticas no desenvolvimento de APIs REST, com uma visão mais criticas do desenvolvimento de software:
- Implementei um sistema de login com Spring Security, configurando o sistema de geração de token e bloqueio de endpoints por roles.
- Implementei retornos com status code e implementei alguma validações no service para dados inexistentes e inconsistentes.
- Refatorei linhas de código no service, removendo redundancias e reduzindo linhas de códigos inuteis.
- Desenvolvi utilizando o Docker, com setup do banco de dados.

Foi realizada em Agosto/Setembro de 2025.


### 3 parte:

Essa é a etapa atual, a refatoração que eu faço hoje, meu aprendizado e maturidade me permitiram olhar a esse projeto com uma visão mais critica do que as outras versões, então decidi implementar:

- Implementei testes automatizados, focados totalmente na legibilidade e manutenção ao longo do tempo.
- Utilizei JUnit5/Mockito para unitários, Testcontainers e RestAssured para integração.
- Implementei uma esteira de CI/CD completa seguindo a documentação do próprio GitHub Actions, que roda testes, builda a imagem e enviar ao DockerHub.


Além disso:

- Tive uma visão mais clara de Clean Arch, e o que os módulos podem acessar diretamente, como módulos de alto nível (service) não devem depender de baixo nível (dtos) e refatorei com base nisso.
- Ganhei familiaridade com a estrutura do pom.xml e uso do Maven, gerenciando depedências ao longo do projeto.
- E obtive experiência procurando e acessando documentações na internet, para resolução dos problemas.


### O projeto não está totalmente completo pois não achei necessário implementar por inteiro, visto que os conceitos e práticas já aprendidos e implementados.

---

### Deploy na AWS 

Também decidi explorar a AWS e realizar o deploy visando aumentar minha familiaridade.

- Criei uma instância do EC2, baixei Docker e rodei a imagem da aplicação.
- Configurei o RDS de forma simples, e conectei a API por meio de variáveis de ambiente.
- Mais entendimento sobre SecurityGroups, IAM e Custos nos recursos Cloud.


### Abaixo algumas imagens do Deploy que ja foi cancelado:

* **Container Docker Rodando:** A execução do *backend* na instância EC2 (ver [Issue #6](https://github.com/kowalskiig/Gerenciador-de-Jogos/issues/6#issue-3459757712)).
* **Teste de Endpoints via Postman:** Demonstração do retorno da API em produção (ver [Issue #3](https://github.com/kowalskiig/Gerenciador-de-Jogos/issues/3#issue-3454867226)).
* **Estrutura de Banco de Dados:** A prova de que o PostgreSQL do RDS estava pronto para receber os dados (ver [Issue #7](https://github.com/kowalskiig/Gerenciador-de-Jogos/issues/7#issue-3459760728)).

---


##  Tecnologias Utilizadas

- Java 21
- Spring Security
- GitHub Actions para CI/CD
- AWS EC2
- AWS RDS
- Docker
- PostgreSQL

---


<p>
  <a href="https://www.linkedin.com/in/gustavokowalski/" target="_blank" style="margin-right: 20px;">
    <img src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white" alt="LinkedIn" />
  </a>
