CREATE TABLE IF NOT EXISTS equipo
(
    id         int auto_increment
        primary key,
    nombre     varchar(60) not null,
    categoria  varchar(32) null,
    disponible tinyint(1)  null
);