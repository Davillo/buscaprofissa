CREATE TABLE area_atuacao(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(70) NOT NULL  
);


CREATE TABLE categoria(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(70) NOT NULL,
    id_area_atuacao BIGINT(20) NOT NULL,
    FOREIGN KEY (id_area_atuacao) REFERENCES area_atuacao(id));