# Sistema de Empréstimo de Ferramentas - Front-end 🛠️

> Repositório para a parte do **front-end** do projeto da A3 de Sistemas Distribuídos.
> O back-end está em [SistemaEmprestimo-Back](https://github.com/turt0/SistemaEmprestimo-Back).

## A3 da UC de Sistemas Distribuídos, 2026

> O sistema está escrito em **Java** e a interface é web (JSP + Servlets).
> A comunicação entre front e back é feita via **SOAP**.

## Grupo

- Caio Candido Cunha, RA 1072413246 (turt0)

## Sobre o projeto

O sistema é um gerenciador de empréstimo de ferramentas distribuído, dividido em dois projetos implantados no mesmo Apache Tomcat 9:

- **Front-end (este repositório)**: aplicação web JSP/Servlet que serve as telas no navegador e consome os serviços SOAP.
- **Back-end** ([SistemaEmprestimo-Back](https://github.com/turt0/SistemaEmprestimo-Back)): expõe três serviços SOAP — `AmigoServico`, `FerramentaServico` e `EmprestimoServico` — que encapsulam o acesso ao banco MySQL.

Por ser distribuído, este front depende do back estar disponível para funcionar.

## Tecnologias

- Java 11
- Apache Tomcat 9.0.117
- MySQL 8.3.0

## Estrutura

```
src/main/java/
├── controle/
├── modelo/
└── servico/
src/main/webapp/
├── index.jsp
├── estilo.css
├── FrmGerenciarAmigo.jsp
├── FrmCadastroAmigo.jsp
├── FrmGerenciarFerramenta.jsp
├── FrmCadastroFerramenta.jsp
├── FrmGerenciarEmprestimo.jsp
├── FrmCadastroEmprestimo.jsp
├── FrmRelatorioEmprestimo.jsp
└── WEB-INF/web.xml
```

## Levantamento de requisitos

### Requisitos funcionais

- **RF01**: O sistema deve permitir o cadastro de ferramentas com informações como nome, marca e custo de aquisição.
- **RF02**: O sistema deve permitir o cadastro de amigos com informações como nome e telefone.
- **RF03**: O usuário deve poder registrar empréstimos de uma ferramenta a um amigo, incluindo data de empréstimo e data de devolução prevista.
- **RF04**: O sistema deve permitir registrar a devolução dos empréstimos.
- **RF05**: O sistema deve gerar um relatório que exiba todas as ferramentas cadastradas e o custo total gasto.
- **RF06**: O sistema deve gerar um relatório com todos os empréstimos realizados, quem fez a maior quantidade de empréstimos, e quem ainda não devolveu.
- **RF07**: O sistema deve fornecer um relatório com os empréstimos ativos no momento.
- **RF08**: O sistema deve, a cada novo empréstimo a um amigo, informar se o amigo ainda não devolveu algo.

### Requisitos não-funcionais

- **RNF01**: O sistema deve rodar em ambiente local com Apache Tomcat 9.
- **RNF02**: O sistema deve ser escrito em Java 11.
- **RNF03**: A interface é web, acessível por navegador em `http://localhost:8080/SistemaEmprestimo-Front/`.
- **RNF04**: O sistema deve ser **distribuído**
- **RNF05**: Front-end e back-end devem se comunicar via SOAP (JAX-WS RPC).

### Regras de negócio

- **RN01**: Pode haver apenas um amigo para cada empréstimo.
- **RN02**: Múltiplas ferramentas podem ser emprestadas para um amigo (um empréstimo por ferramenta).
- **RN03**: A manipulação de Amigos, Ferramentas, e Empréstimos é responsabilidade do back-end.

## Configuração do cliente SOAP

A URL do back-end fica centralizada em `src/main/java/servico/ControleServico.java`:

```java
private static final String URL_BASE =
    "http://localhost:8080/webservice_emprestimo_jaxws_soap_tomcat";
```

Para apontar o front para um back-end em outra máquina, basta alterar essa constante.

## Como executar

1. Subir antes o back-end conforme as instruções de [SistemaEmprestimo-Back](https://github.com/turt0/SistemaEmprestimo-Back).
2. Subir o Apache Tomcat 9 na porta 8080.
3. Buildar este projeto e fazer deploy no Tomcat.
4. Acessar `http://localhost:8080/SistemaEmprestimo-Front/` no navegador.
