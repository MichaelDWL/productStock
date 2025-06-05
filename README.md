# Sistema de Gerenciamento de Estoque Hospitalar (Java + MySQL)

Este é um sistema de gerenciamento de estoque voltado para o ambiente hospitalar, com controle de entrada e saída de produtos, 
permissões por tipo de usuário e geração de relatórios. O sistema foi desenvolvido em Java com persistência em MySQL.

## 📋 Funcionalidades

- Login com autenticação por cargo (Admin, Almoxarife, Auxiliar, Farmacêutico)
- Cadastro e consulta de produtos
- Entrada de notas fiscais
- Baixa de estoque para pacientes e setores
- Inventário e atualização de dados
- Criação e gerenciamento de usuários
- Geração de relatório de posição de estoque com exportação para CSV

---

## ⚙️ Requisitos

- Java JDK 8 ou superior
- MySQL Server
- Conector JDBC para MySQL (`mysql-connector-java-x.x.xx.jar`)
- IDE (recomendado: NetBeans, Eclipse ou IntelliJ)
- Terminal para execução em modo texto

---

## 🧰 Estrutura do Projeto

```
sygerenciamentoestoquea3/
├── conexaodb.java         # Conexão com o banco de dados
├── funcoes.java           # Métodos do sistema (CRUD, relatórios, etc)
├── cargo.java             # Menus com permissões por cargo
├── SyGerenciamentoEstoqueA3.java  # Classe principal (main)

```

---

## 🛠️ Como Compilar

1. **Clone o repositório** ou copie os arquivos `.java` para sua IDE.

2. **Adicione o driver JDBC** ao seu projeto:
   - Baixe em: https://dev.mysql.com/downloads/connector/j/
   - Adicione o `.jar` como biblioteca no seu projeto.

3. **Compile os arquivos Java** no terminal:
```
bash
javac -cp .;mysql-connector-java-x.x.xx.jar sygerenciamentoestoquea3/*.java
```

> No Linux/Mac, use `:` no lugar de `;`

---

## ▶️ Como Executar

Execute a classe principal:
```
bash
java -cp .;mysql-connector-java-x.x.xx.jar sygerenciamentoestoquea3.SyGerenciamentoEstoqueA3
```

---

## 🗃️ Banco de Dados

O sistema cria automaticamente o banco de dados chamado `estoque` com as tabelas necessárias na primeira execução, incluindo um usuário ADMIN padrão:

- **Login:** ADMIN  
- **Senha:** 0000

---

## 📝 Observações

- A interface é baseada em linha de comando (terminal).
- Relatórios CSV são salvos em:  
  `C:/Users/[SeuUsuario]/OneDrive/Área de Trabalho/relatorios/` (pode ser alterado em `funcoes.java`).
- Para redefinir a senha de um usuário, use a opção disponível no menu de Admin.
- O primeiro login será feito utilizando o "ADMIN", nele você cria os cadastros para os demais cargos, para ter acessos as outras funcionalidades.

---

## 📚 Licença

Uso acadêmico para fins educacionais.
