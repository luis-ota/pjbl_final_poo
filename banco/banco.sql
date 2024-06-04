DROP DATABASE IF EXISTS medicaly;
CREATE DATABASE medicaly;
USE medicaly;

CREATE TABLE `Pacientes` (
                             `Id` CHAR(36) NOT NULL,
                             `Nome` VARCHAR(100) NOT NULL,
                             `Sobrenome` VARCHAR(100) NOT NULL,
                             `Cpf` VARCHAR(100) NOT NULL,
                             PRIMARY KEY (`Id`)
);

CREATE TABLE `Profissionais` (
                                 `Id` CHAR(36) NOT NULL,
                                 `Nome` VARCHAR(100) NOT NULL,
                                 `Sobrenome` VARCHAR(100) NOT NULL,
                                 `Especialidades` VARCHAR(100) NOT NULL,
                                 PRIMARY KEY (`Id`)
);

INSERT INTO `Pacientes` (`Id`, `Nome`, `Sobrenome`, `Cpf`)
VALUES
    (UUID(), 'João', 'Silva', '12345678901'),
    (UUID(), 'Maria', 'Santos', '23456789012'),
    (UUID(), 'José', 'Oliveira', '34567890123'),
    (UUID(), 'Ana', 'Souza', '45678901234');

-- Inserts para tabela Profissionais
INSERT INTO `Profissionais` (`Id`, `Nome`, `Sobrenome`, `Especialidades`)
VALUES
    (UUID(), 'Pedro', 'Ribeiro', 'Clínica Médica'),
    (UUID(), 'Juliana', 'Martins', 'Pediatria'),
    (UUID(), 'Rafael', 'Almeida', 'Odontologia'),
    (UUID(), 'Carla', 'Ferreira', 'Psicologia');
