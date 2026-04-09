# Sistema de Empréstimo de Ferramentas 🛠️
## A3 da UC de Programação de soluções computacionais, Primeiro semestre, 2024.
> [!NOTE]
> O Sistema está escrito em Java 1.8 e usa um banco de dados MySQL 8.3.0  

## Componentes:
- Arthur Ramos Bilsland (Aarthaz, USER, 1072419595)  
- Caio Candido Cunha (turt0, caio, Pichau)  
- Pedro Otávio de Jesus da Rosa (PedroOtavioDeJesus)  

## Levantamento de requisitos:  
### Requisitos funcionais:  
- RF01: O sistema deve permitir o cadastro de ferramentas com informações como nome, marca e custo de aquisição.  
- RF02: O sistema deve permitir o cadastro de amigos com informações como nome e telefone.  
- RF03: O usuário deve poder registrar empréstimos de uma ou mais ferramentas a um amigo, incluindo data de empréstimo e data de devolução.
- RF04: O sistema deve permitir registrar a devolução dos empréstimos.  
- RF05: O sistema deve gerar um relatório que exiba todas as ferramentas cadastradas e o total gasto pelo seu avô.  
- RF06: O sistema deve gerar um relatório com todos os empréstimos realizados, quem fez a maior quantidade de empréstimos, e quem ainda não devolveu. 
- RF07: O sistema deve fornecer um relatório com os empréstimos ativos no momento.  
- RF08: O sistema deve, à cada novo emprestimo a um amigo, informar se o amigo ainda não devolveu algo.  

### Requisitos não-funcionais:  
- RNF01: O sistema deve rodar localmente no computador.  
- RNF02: O sistema deve ser escrito em Java 1.8
- RNF03: O sistema usará um banco de dados MySQL 8.3.0
- RNF04: O sistema usará o sistema operacional Windows 10

### Regras de negócios  
- RN01: Pode haver apenas um amigo para cada empréstimo.  
- RN02: Múltiplas ferramentas podem ser emprestadas para um amigo.  

## Configurações do Banco de Dados:  
- user: root  
- senha: 1234  
##
![image](https://github.com/Aarthaz/SistemaEmprestimo/assets/165083337/e80242bf-145a-44e5-8cb2-becfed29d41a)![image](https://github.com/Aarthaz/SistemaEmprestimo/assets/165083337/143c9563-511a-4999-9b04-b5cae35511ec)
