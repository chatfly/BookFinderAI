<h1>Book Finder AI</h1>
> Uma aplicação desenvolvida com o foco de sugerir livros para o usuário, baseando-se em seu gosto literário.

---

## 📚 Sobre

Esse projeto foi feito com o intuito de praticar conceitos aprendidos sobre Inteligência Artificial

### 🐱‍🏍 O quê a aplicação oferece:
- Um sistema CRUD para cadastro de livros
- Livros com atribuição de nota
- Endpoint para fazer uma requisição na API da OpenAI e sugerir um livro

## ⚙ Ferramentas usadas:

### Java
- Utiliza a versão 17 do Java
- Uma linguagem focada no desempenho e na escalabilidade

### SpringBoot
- Uma Framework do Java voltada a construção de APIs
- Nessa aplicação, SpringBoot é utilizado na construção das endpoints da API

### WebFlux
- Uma dependência do SpringBoot
- É utilizada na aplicação para fazer requisições asíncronas no modelo de IA

### PostgreSQL
- É um banco de dados
- Integramos esse banco de dados na aplicação, para salvar os livros cadastrados

### FlyWay
- Uma dependência do SpringBoot para fazer Migrations na DB
- Integramos o FlyWay no projeto para criar migrations de criação de tabelas

### Lombok
- Dependência do SpringBoot com foco na diminuição de código
- O projeto faz uso do Lombok para remover código boilerplate

---

## 👩‍💻 Como rodar o projeto localmente

### Requisitos
- **Chave de API OpenAI** para conseguir se conectar com o modelo de IA
- **Java 17 ou superior**
- **Docker** para rodar a DB de uma forma mais prática

### Configurando
- É necessário rodar um container Docker com postgres, da seguinte maneira
  <a>docker run -e POSTGRES_DB=bookfinder -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres</a>
- Criar um arquivo .env na raiz do projeto e criar uma variável de ambiente "OPENAI_API_KEY" para guardar a chave de uma maneira segura

### Rodando
- Agora é só rodar a aplicação usando Maven ou de outra forma de sua preferência
