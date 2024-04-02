# Ponto de Venda (PDV)

Este é um projeto de um sistema de ponto de venda (PDV) desenvolvido para gerenciar operações de vendas em uma loja. O sistema oferece funcionalidades como registro de vendas, adição de produtos ao carrinho, recebimento de pagamentos e fechamento de caixa.

## Tecnologias Utilizadas

### Backend
- Linguagem de Programação: Java
- Framework Web: Spring Boot
- Banco de Dados: MySQL (produção), H2 (teste)
- ORM: Hibernate
- Autenticação e Autorização: Spring Security
- API RESTful: Spring MVC
- Ferramenta de Build: Maven
- Testes: JUnit, Mockito

### Frontend
- Biblioteca de Interface: React.js
- Estilização: Material-UI, Styled Components
- Gerenciamento de Estado: React Hooks (useState, useEffect)
- Requisições HTTP: Axios
- Controle de Rotas: React Router
- Autenticação: Context API

## Funcionalidades

- Abrir e fechar o caixa
- Adicionar produtos ao carrinho
- Registrar vendas
- Receber pagamentos
- Visualizar subtotal, total e troco

## Como Executar o Projeto

1. **Backend:**
   - Navegue até o diretório `backend`
   - Execute o comando `mvn spring-boot:run` para iniciar o servidor backend

2. **Frontend:**
   - Navegue até o diretório `frontend`
   - Execute o comando `npm install` para instalar as dependências
   - Execute o comando `npm start` para iniciar o servidor de desenvolvimento do frontend

## Próximos Passos

- Implementar testes automatizados (JUnit, Mockito)
- Aprimorar tratamento de erros e mensagens de feedback
- Otimizar o desempenho do sistema
- Escrever documentação detalhada

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir uma issue ou enviar um pull request com melhorias, correções de bugs ou novas funcionalidades.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).
