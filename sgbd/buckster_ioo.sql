create database buckster_ioo;

use buckster_ioo;

-- Tabela principal da herança
CREATE TABLE pessoa (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- Tabela especializada: Cliente
CREATE TABLE cliente (
    id INT PRIMARY KEY,
    curso VARCHAR(100),
    telefone VARCHAR(20),

    CONSTRAINT fk_cliente_pessoa
    FOREIGN KEY (id)
    REFERENCES pessoa(id)
);

-- Tabela especializada: Usuario
CREATE TABLE usuario (
    id INT PRIMARY KEY,
    email VARCHAR(150) NOT NULL,
    login VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,

    CONSTRAINT fk_usuario_pessoa
    FOREIGN KEY (id)
    REFERENCES pessoa(id)
);

CREATE TABLE armarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    status VARCHAR(20) NOT NULL,
    cliente_id INT NULL,

    CONSTRAINT fk_armario_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

CREATE TABLE ordemreserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id INT NOT NULL,
    armario_id INT NOT NULL,
    usuario_id INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,

    CONSTRAINT fk_ordem_cliente
        FOREIGN KEY (cliente_id) REFERENCES cliente(id),

    CONSTRAINT fk_ordem_armario
        FOREIGN KEY (armario_id) REFERENCES armarios(id),

    CONSTRAINT fk_ordem_usuario
        FOREIGN KEY (usuario_id) REFERENCES usuario(id)
);
