INSERT INTO tipo_rol (nombre)
VALUES ('ADMIN');
INSERT INTO tipo_rol (nombre)
VALUES ('USER');
INSERT INTO tipo_rol (nombre)
VALUES ('MARKETING');

INSERT INTO rol (id_tipo_rol)
VALUES (1);
INSERT INTO rol (id_tipo_rol)
VALUES (2);
INSERT INTO rol (id_tipo_rol)
VALUES (3);


INSERT INTO local (nombre)
VALUES ('Miraflores'),
       ('Surco'),
       ('San Isidro'),
       ('Barranco'),
       ('La Molina');

INSERT INTO tipo_juego (nombre)
VALUES ('cartas'),
       ('ruleta'),
       ('tragamonedas'),
       ('dados');


INSERT INTO juego (nombre, id_tipo_juego)
VALUES ('Texas Hold', 2),
       ('Blackjack', 2),
       ('Póker Chino', 2),
       ('Baccarat', 2),
       ('Ruleta Europea', 1),
       ('Ruleta Americana', 1),
       ('Tragamonedas 777', 3),
       ('Tragamonedas Frutas', 3);

INSERT INTO persona (nombre, apellido_paterno, apellido_materno, dni, genero, telefono, direccion, fecha_nacimiento)
VALUES ('Raul', 'Moreno', 'Postigo', '77777777', true, '986294178', 'Calle Loma Chica 144', '1990-05-15'),
       ('Carlos', 'Garcia', 'Lopez', '77777778', true, '987654321', 'Av. Libertador 300', '1985-02-20'),
       ('Maria', 'Pérez', 'Gonzalez', '77777779', false, '986567890', 'Calle El Sol 456', '1992-08-10'),
       ('Luis', 'Martínez', 'Rodriguez', '77777780', true, '988123456', 'Calle Primavera 789', '1988-11-05'),
       ('Ana', 'Hernandez', 'Martínez', '77777781', false, '989654321', 'Av. Brasil 101', '1995-01-25'),
       ('Pedro', 'Sánchez', 'Ramirez', '77777782', true, '980987654', 'Calle San Juan 987', '1993-12-12'),
       ('Laura', 'Fernández', 'Gómez', '77777783', false, '981234567', 'Calle Lima 321', '1987-07-15'),
       ('Javier', 'Rodríguez', 'Fernández', '77777784', true, '982345678', 'Av. Pardo 234', '1990-03-30'),
       ('Claudia', 'Gómez', 'Vásquez', '77777785', false, '983456789', 'Calle Santa Rosa 555', '1994-06-25'),
       ('Andrés', 'Jiménez', 'Reyes', '77777786', true, '984567890', 'Calle Los Olivos 678', '1989-09-17');


INSERT INTO usuario (activo, fecha_registro, foto, password, username, id_persona)
VALUES (true, '2025-03-18', 'foto1.jpg', 'pass123', 'user1', 1),
       (true, '2025-03-18', 'foto2.jpg', 'pass123', 'user2', 2),
       (true, '2025-03-18', 'foto3.jpg', 'pass123', 'user3', 3),
       (true, '2025-03-18', 'foto4.jpg', 'pass123', 'user4', 4),
       (true, '2025-03-18', 'foto5.jpg', 'pass123', 'user5', 5),
       (true, '2025-03-18', 'foto6.jpg', 'pass123', 'user6', 6),
       (true, '2025-03-18', 'foto7.jpg', 'pass123', 'user7', 7),
       (true, '2025-03-18', 'foto8.jpg', 'pass123', 'user8', 8),
       (true, '2025-03-18', 'foto9.jpg', 'pass123', 'user9', 9),
       (true, '2025-03-18', 'foto10.jpg', 'pass123', 'user10', 10);

INSERT INTO usuario_rol (id_usuario, id_rol)
SELECT id_usuario, 2
FROM usuario
WHERE id_usuario BETWEEN 1 AND 10;

INSERT INTO jugador (id_usuario)
SELECT id_usuario
FROM usuario
WHERE id_usuario BETWEEN 1 AND 10;


INSERT INTO tarjeta (id_jugador, monto, codigo)
SELECT
    j.id_jugador,
    (j.id_jugador * 100) AS monto,
    CONCAT(p.dni, UPPER(LEFT(p.nombre, 1)), UPPER(LEFT(p.apellido_paterno, 1))) AS codigo
FROM jugador j
         JOIN usuario u ON j.id_usuario = u.id_usuario
         JOIN persona p ON u.id_persona = p.id_persona
WHERE j.id_jugador BETWEEN 1 AND 10;

