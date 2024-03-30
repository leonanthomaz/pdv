# PDV (Ponto de Venda)

O PDV (Ponto de Venda) é um sistema completo de caixa registrado que permite aos usuários gerenciar vendas, estoques e transações financeiras de forma eficiente. O sistema foi desenvolvido utilizando Java com Spring Boot para o servidor, Spring Security para autenticação com JWT (JSON Web Tokens) para segurança, e React com Material UI e Styled Components para o frontend.

## Funcionalidades Principais

- Registro de vendas de produtos.
- Controle de estoque.
- Autenticação de usuários com geração de token JWT.
- Cálculo de subtotal, total e troco.
- Gerenciamento de abertura e fechamento de caixa.
- Interface amigável e responsiva com Material UI e Styled Components.

## Tecnologias Utilizadas

### Backend (Java Spring Boot)

- **Spring Boot:** Framework para criação de aplicativos Java baseados em Spring.
- **Spring Security:** Estrutura de segurança para autenticação e autorização.
- **JWT (JSON Web Tokens):** Mecanismo para autenticação de usuários e geração de tokens seguros.
- **Spring Data JPA:** Facilita a interação com bancos de dados relacionais.
- **Spring Web:** Suporte para criar aplicativos da web usando Spring MVC e RESTful.

### Frontend (React)

- **React:** Biblioteca JavaScript para construir interfaces de usuário.
- **Material UI:** Biblioteca de componentes React para um design rápido e responsivo.
- **Styled Components:** Biblioteca para estilizar componentes React com estilos baseados em JavaScript.

## Instalação e Configuração

1. Clone o repositório: `git clone https://github.com/seu-usuario/PDV.git`
2. Configure as variáveis de ambiente no arquivo `.env` do frontend:
```
REACT_APP_API_URL=http://localhost:8080
```
4. Inicie o servidor backend: `mvn spring-boot:run` (dentro da pasta do projeto Java Spring Boot)
5. Inicie o servidor frontend: `npm start` (dentro da pasta do projeto React)

## Uso

1. Faça login com suas credenciais.
2. Após o login bem-sucedido, você poderá acessar a tela de abertura de caixa.
3. Durante a operação do PDV, você pode escanear o código de barras dos produtos e adicionar à lista de vendas.
4. Ao fechar a venda, o sistema calculará o subtotal, o valor recebido e o troco.

## Contribuição

- Clone o repositório: `git clone https://github.com/seu-usuario/PDV.git`
- Crie uma nova branch: `git checkout -b nova-funcionalidade`
- Faça suas alterações e commit: `git commit -am 'Adicionar nova funcionalidade'`
- Push para a branch: `git push origin nova-funcionalidade`
- Envie um pull request

## Licença

Este projeto está licenciado sob a [MIT License](https://opensource.org/licenses/MIT).
