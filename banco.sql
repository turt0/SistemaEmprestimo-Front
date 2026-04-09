-- Remove o banco antigo, caso exista.
DROP DATABASE IF EXISTS db_ferramentas;

-- Cria o banco principal do sistema.
CREATE DATABASE db_ferramentas
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

-- Seleciona o banco para os próximos comandos.
USE db_ferramentas;

-- Cria a tabela de amigos.
CREATE TABLE tb_amigo (
    id INT NOT NULL,
    nome VARCHAR(120) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- Cria a tabela de ferramentas.
CREATE TABLE tb_ferramentas (
    id INT NOT NULL,
    nome VARCHAR(120) NOT NULL,
    marca VARCHAR(120) NOT NULL,
    custo DECIMAL(10,2) NOT NULL DEFAULT 0.00,
    PRIMARY KEY (id)
);

-- Cria a tabela de empréstimos.
CREATE TABLE tb_emprestimos (
    id INT NOT NULL AUTO_INCREMENT,
    id_amigo INT NOT NULL,
    id_ferramenta INT NOT NULL,
    dt_emprestimo DATE NOT NULL,
    dt_devolucao_prevista DATE NOT NULL,
    dt_devolucao_real DATE NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_emprestimo_amigo
        FOREIGN KEY (id_amigo) REFERENCES tb_amigo(id),
    CONSTRAINT fk_emprestimo_ferramenta
        FOREIGN KEY (id_ferramenta) REFERENCES tb_ferramentas(id)
);

-- Cria índice para consultas por amigo.
CREATE INDEX idx_emprestimo_amigo ON tb_emprestimos(id_amigo);

-- Cria índice para consultas por ferramenta.
CREATE INDEX idx_emprestimo_ferramenta ON tb_emprestimos(id_ferramenta);

-- Cria índice para consultas por devolução.
CREATE INDEX idx_emprestimo_devolucao_real ON tb_emprestimos(dt_devolucao_real);
