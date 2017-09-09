CREATE TABLE servico (
	id_usuario BIGINT(20) NOT NULL,
    id_categoria BIGINT(20) NOT NULL,
    PRIMARY KEY(id_usuario,id_categoria),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
    
);