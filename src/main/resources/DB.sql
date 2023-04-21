DROP DATABASE IF EXISTS proyectodual;
CREATE DATABASE proyectodual;

USE proyectodual;

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username varchar(32) NOT NULL,
    password varchar(64) NOT NULL,
    email varchar(32) NOT NULL,
    admin int NOT NULL -- 0/1
);

DROP TABLE IF EXISTS servicio;
CREATE TABLE servicio(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    plataforma varchar(32) NOT NULL,
    url varchar(64) NOT NULL
);

DROP TABLE IF EXISTS grupo;
CREATE TABLE grupo(
    id_servicio int NOT NULL,
    id_user1 int NOT NULL,
    id_user2 int NOT NULL,
    id_user3 int,
    id_user4 int,
    id_user5 int,
    id_user6 int,
    id_user7 int,
    id_user8 int,

    CONSTRAINT fk_servicio FOREIGN KEY (id_servicio) REFERENCES servicio(id),
    CONSTRAINT fk_user1 FOREIGN KEY (id_user1) REFERENCES usuario(id),
    CONSTRAINT fk_user2 FOREIGN KEY (id_user2) REFERENCES usuario(id),
    CONSTRAINT fk_user3 FOREIGN KEY (id_user3) REFERENCES usuario(id),
    CONSTRAINT fk_user4 FOREIGN KEY (id_user4) REFERENCES usuario(id),
    CONSTRAINT fk_user5 FOREIGN KEY (id_user5) REFERENCES usuario(id),
    CONSTRAINT fk_user6 FOREIGN KEY (id_user6) REFERENCES usuario(id),
    CONSTRAINT fk_user7 FOREIGN KEY (id_user7) REFERENCES usuario(id),
    CONSTRAINT fk_user8 FOREIGN KEY (id_user8) REFERENCES usuario(id)
);

DROP TABLE IF EXISTS post;
CREATE TABLE post(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_user int NOT NULL,
    message text NOT NULL,
    id_servicio int,
    create_at DATE NOT NULL,

    CONSTRAINT fk_id_user FOREIGN KEY (id_user) REFERENCES usuario(id),
    CONSTRAINT fk_id_servicio FOREIGN KEY (id_servicio) REFERENCES servicio(id)
);