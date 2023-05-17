DROP DATABASE IF EXISTS proyectodual;
CREATE DATABASE proyectodual;
USE proyectodual;

CREATE TABLE usuario(
    id int PRIMARY KEY AUTO_INCREMENT,
    usuario varchar(32) NOT NULL UNIQUE,
    pass varchar(64) NOT NULL,
    email varchar(64) NOT NULL UNIQUE,
    img_perfil varchar(64),
    nacimiento DATE, -- YYYY-MM-DD
    admin BOOL NOT NULL -- 0/1
);

CREATE TABLE servicio(
    id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(64) NOT NULL,
    precio float(2,2),
    web varchar(32)
);

CREATE TABLE grupo(
    id int PRIMARY KEY AUTO_INCREMENT,
    servicio int NOT NULL,
    user1 int NOT NULL,
    user2 int NOT NULL,
    user3 int,
    user4 int,
    user5 int,
    user6 int,
    user7 int,
    user8 int,
    CONSTRAINT FK_servicio FOREIGN KEY (servicio) REFERENCES servicio(id),
    CONSTRAINT FK_user1 FOREIGN KEY (user1) REFERENCES usuario(id),
    CONSTRAINT FK_user2 FOREIGN KEY (user2) REFERENCES usuario(id),
    CONSTRAINT FK_user3 FOREIGN KEY (user3) REFERENCES usuario(id),
    CONSTRAINT FK_user4 FOREIGN KEY (user4) REFERENCES usuario(id),
    CONSTRAINT FK_user5 FOREIGN KEY (user5) REFERENCES usuario(id),
    CONSTRAINT FK_user6 FOREIGN KEY (user6) REFERENCES usuario(id),
    CONSTRAINT FK_user7 FOREIGN KEY (user7) REFERENCES usuario(id),
    CONSTRAINT FK_user8 FOREIGN KEY (user8) REFERENCES usuario(id)
);


-- TWITTER
CREATE TABLE tablon(
    id int PRIMARY KEY AUTO_INCREMENT,
    mensage varchar(144) NOT NULL,
    id_user int NOT NULL,
    likes int NOT NULL DEFAULT 0,
    create_at TIMESTAMP(6) NOT NULL,
    CONSTRAINT FK_id_user FOREIGN KEY (id_user) REFERENCES usuario(id)
);