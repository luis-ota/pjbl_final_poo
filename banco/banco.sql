CREATE DATABASE IF NOT EXISTS medicaly;
USE medicaly;

CREATE TABLE `Enderecos` (
                             `Id` CHAR(36) NOT NULL,
                             `Cep` VARCHAR(100) NOT NULL,
                             `Estado` VARCHAR(100) NOT NULL,
                             `Logradouro` VARCHAR(100) NOT NULL,
                             `Numero` VARCHAR(100) NOT NULL,
                             `Bairro` VARCHAR(100) NOT NULL,
                             `Cidade` VARCHAR(100) NOT NULL,
                             `CodigoIbgeCidade` VARCHAR(100) NOT NULL,
                             `Complemento` VARCHAR(100) NOT NULL,
                             `Observacao` VARCHAR(100) NOT NULL,
                             PRIMARY KEY (`Id`)
);

CREATE TABLE `Pacientes` (
                             `Id` CHAR(36) NOT NULL,
                             `Nome` VARCHAR(100) NOT NULL,
                             `Sobrenome` VARCHAR(100) NOT NULL,
                             `Cpf` VARCHAR(100) NOT NULL,
                             `Email` VARCHAR(100) NOT NULL,
                             `Telefone` VARCHAR(100) NOT NULL,
                             `DataNascimento` VARCHAR(100) NOT NULL,
                             `Genero` VARCHAR(100) NOT NULL,
                             `EnderecoId` CHAR(36) NOT NULL,
                             PRIMARY KEY (`Id`)
);

CREATE TABLE `Profissionais` (
                                 `Id` CHAR(36) NOT NULL,
                                 `Nome` VARCHAR(100) NOT NULL,
                                 `Sobrenome` VARCHAR(100) NOT NULL,
                                 `Cpf` VARCHAR(100) NOT NULL,
                                 `Email` VARCHAR(100) NOT NULL,
                                 `Telefone` VARCHAR(100) NOT NULL,
                                 `DataNascimento` VARCHAR(100) NOT NULL,
                                 `Genero` VARCHAR(100) NOT NULL,
                                 `EnderecoId` CHAR(36),
                                 `CredencialDeSaúde` VARCHAR(100) NOT NULL,
                                 `Atuações` VARCHAR(100) NOT NULL,
                                 `Especialidades` VARCHAR(100) NOT NULL,
                                 `Tipo` VARCHAR(100) NOT NULL,
                                 `InicioExpediente` VARCHAR(100) NOT NULL,
                                 `FimExpediente` VARCHAR(100) NOT NULL,
                                 PRIMARY KEY (`Id`)
);

-- Inserts para tabela Enderecos
INSERT INTO `Enderecos` (`Id`, `Cep`, `Estado`, `Logradouro`, `Numero`, `Bairro`, `Cidade`, `CodigoIbgeCidade`, `Complemento`, `Observacao`) VALUES
                                                                                                                                                 (UUID(), '12345678', 'SP', 'Rua A', '123', 'Centro', 'São Paulo', '1234567', 'Apto 101', 'Próximo à estação de metrô'),
                                                                                                                                                 (UUID(), '87654321', 'RJ', 'Avenida B', '456', 'Copacabana', 'Rio de Janeiro', '7654321', 'Casa', 'Perto da praia'),
                                                                                                                                                 (UUID(), '45678901', 'MG', 'Rua C', '789', 'Santo Antônio', 'Belo Horizonte', '9876543', 'Sala 202', 'Edifício comercial'),
                                                                                                                                                 (UUID(), '21098765', 'RS', 'Avenida D', '987', 'Centro', 'Porto Alegre', '6543210', 'Loja 303', 'Ao lado do shopping');

-- Inserts para tabela Pacientes
INSERT INTO `Pacientes` (`Id`, `Nome`, `Sobrenome`, `Cpf`, `Email`, `Telefone`, `DataNascimento`, `Genero`, `EnderecoId`) VALUES
                                                                                                                              (UUID(), 'João', 'Silva', '12345678901', 'joao@example.com', '11987654321', '1990-05-20', '1', (SELECT `Id` FROM `Enderecos` LIMIT 1)),
                                                                                                                              (UUID(), 'Maria', 'Santos', '23456789012', 'maria@example.com', '21987654321', '1985-08-15', '2', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 1)),
                                                                                                                              (UUID(), 'José', 'Oliveira', '34567890123', 'jose@example.com', '31987654321', '1978-12-10', '1', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 2)),
                                                                                                                              (UUID(), 'Ana', 'Souza', '45678901234', 'ana@example.com', '31987654321', '2000-02-28', '2', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 3));

-- Inserts para tabela Profissionais
INSERT INTO `Profissionais` (`Id`, `Nome`, `Sobrenome`, `Cpf`, `Email`, `Telefone`, `DataNascimento`, `Genero`, `EnderecoId`, `CredencialDeSaúde`, `Atuações`, `Especialidades`, `Tipo`, `InicioExpediente`, `FimExpediente`) VALUES
                                                                                                                                                                                                                                  (UUID(), 'Pedro', 'Ribeiro', '56789012345', 'pedro@example.com', '11987654321', '1980-03-15', '1', (SELECT `Id` FROM `Enderecos` LIMIT 1), 'CRM12345', 'Clínico Geral', 'Clínica Médica', '1', '08:00:00', '17:00:00'),
                                                                                                                                                                                                                                  (UUID(), 'Juliana', 'Martins', '67890123456', 'juliana@example.com', '21987654321', '1975-07-25', '2', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 1), 'CRM23456', 'Pediatra', 'Pediatria', '1', '09:00:00', '18:00:00'),
                                                                                                                                                                                                                                  (UUID(), 'Rafael', 'Almeida', '78901234567', 'rafael@example.com', '31987654321', '1988-10-30', '1', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 2), 'CRM34567', 'Dentista', 'Odontologia', '2', '10:00:00', '19:00:00'),
                                                                                                                                                                                                                                  (UUID(), 'Carla', 'Ferreira', '89012345678', 'carla@example.com', '41987654321', '1995-04-05', '2', (SELECT `Id` FROM `Enderecos` LIMIT 1 OFFSET 3), 'CRM45678', 'Psicóloga', 'Psicologia', '2', '10:00:00', '19:00:00');
