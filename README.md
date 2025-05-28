# pjbl_final_poo  
Projeto final da disciplina de Programação Orientada a Objetos (POO)  

Este repositório contém o projeto final desenvolvido para a disciplina de **Programação Orientada a Objetos (POO)**. O sistema implementa um **CRUD completo** para gerenciamento de dados de pacientes e profissionais em um ambiente desktop, utilizando **Java Swing** para a interface gráfica e **MySQL** como banco de dados. O projeto segue boas práticas de POO, como encapsulamento, modularidade e separação de responsabilidades.

---

## Funcionalidades Principais  
- **CRUD completo**: Cadastro, leitura, atualização e exclusão de registros de pacientes e profissionais.  
- **Interface gráfica**: Desenvolvida com **Java Swing**, oferecendo interatividade intuitiva para usuários desktop.  
- **Persistência em MySQL**: Armazenamento seguro de dados em banco relacional, com scripts SQL fornecidos.  
- **Arquitetura modular**: Divisão clara entre camadas (controladores, modelos, DAO, utilitários) para facilitar manutenção e escalabilidade.  

---

## Estrutura do Projeto  

```
pjbl_final_poo/
├── .idea/                  → Configurações do IntelliJ IDEA (arquivos XML de projeto) 
├── banco/                  → Arquivos relacionados ao banco de dados:
│   ├── banco.sql           → Script para criação das tabelas e inserção de dados
│   └── mysql-connector-j-8.0.33.jar → Driver JDBC para conexão com o banco
├── src/                    → Código-fonte organizado por pacotes:
│   ├── controller/         → Classes de controle para gerenciar a lógica de negócios
│   │   ├── PacienteController.java
│   │   └── ProfissionalController.java
│   ├── dao/                → Implementação de padrões DAO para acesso a dados
│   │   ├── AbstractDAO.java
│   │   ├── DAO.java
│   │   └── DAOException.java
│   ├── models/             → Classes de modelo representando entidades do sistema
│   │   ├── Paciente.java
│   │   ├── Profissional.java
│   │   └── User.java
│   ├── utils/              → Classes utilitárias
│   │   └── ConvertMap.java
│   ├── view/               → Interface gráfica desenvolvida com Swing
│   │   └── MedicalyInterface.java
│   └── Main.java           → Classe principal que inicia a aplicação
├── .gitignore              → Arquivos e pastas ignorados pelo versionamento
└── pjbl_final.iml          → Arquivo de configuração do módulo do IntelliJ IDEA 
```

---

## Tecnologias Utilizadas  
- **Java 8+**: Linguagem principal e funcionalidades orientadas a objetos.  
- **Swing**: Framework para construção da interface gráfica desktop.  
- **MySQL**: Banco de dados relacional para persistência de dados.  
- **JDBC**: Conexão e manipulação do banco de dados via Java.  
- **IntelliJ IDEA**: IDE utilizada para desenvolvimento (configurações armazenadas no `.idea/` e `.iml`) .  

---

## Conceitos de POO Aplicados  
- **Encapsulamento**: Atributos privados com métodos de acesso controlado (getters/setters).  
- **Herança**: Uso de classes abstratas (ex.: `AbstractDAO`).  
- **Polimorfismo**: Implementação flexível de métodos nas classes filhas.  
- **Separação de responsabilidades**: Divisão em camadas (model, view, controller, DAO).  

---

## Melhorias Futuras  
- Adicionar autenticação de usuários.  
- Implementar validações mais robustas para entrada de dados.  
- Incluir suporte para exportação de relatórios (PDF ou CSV).  

---

**Desenvolvido como parte do aprendizado prático em Programação Orientada a Objetos na PUCPR.**  
