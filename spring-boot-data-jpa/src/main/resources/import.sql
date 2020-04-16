/* Populate tables */
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Andres', 'Guzman', 'profesor@bolsadeideas.com', '2017-08-01');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('John', 'Doe', 'john.doe@gmail.com', '2017-08-02');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Linus', 'Torvalds', 'linus.torvalds@gmail.com', '2017-08-03');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Jane', 'Doe', 'jane.doe@gmail.com', '2017-08-04');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Rasmus', 'Lerdorf', 'rasmus.lerdorf@gmail.com', '2017-08-05');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Erich', 'Gamma', 'erich.gamma@gmail.com', '2017-08-06');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Richard', 'Helm', 'richard.helm@gmail.com', '2017-08-07');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Ralph', 'Johnson', 'ralph.johnson@gmail.com', '2017-08-08');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('John', 'Vlissides', 'john.vlissides@gmail.com', '2017-08-09');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('James', 'Gosling', 'james.gosling@gmail.com', '2017-08-010');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Bruce', 'Lee', 'bruce.lee@gmail.com', '2017-08-11');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Johnny', 'Doe', 'johnny.doe@gmail.com', '2017-08-12');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('John', 'Roe', 'john.roe@gmail.com', '2017-08-13');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Jane', 'Roe', 'jane.roe@gmail.com', '2017-08-14');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Richard', 'Doe', 'richard.doe@gmail.com', '2017-08-15');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Janie', 'Doe', 'janie.doe@gmail.com', '2017-08-16');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Phillip', 'Webb', 'phillip.webb@gmail.com', '2017-08-17');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Stephane', 'Nicoll', 'stephane.nicoll@gmail.com', '2017-08-18');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Sam', 'Brannen', 'sam.brannen@gmail.com', '2017-08-19');  
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Juergen', 'Hoeller', 'juergen.Hoeller@gmail.com', '2017-08-20'); 
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Janie', 'Roe', 'janie.roe@gmail.com', '2017-08-21');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('John', 'Smith', 'john.smith@gmail.com', '2017-08-22');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Joe', 'Bloggs', 'joe.bloggs@gmail.com', '2017-08-23');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('John', 'Stiles', 'john.stiles@gmail.com', '2017-08-24');
INSERT INTO clientes (nombre, apellidos, email, create_at) VALUES('Richard', 'Roe', 'stiles.roe@gmail.com', '2017-08-25');

/* Populate tabla productos */
INSERT INTO productos (nombre, precio, create_at) VALUES('Panasonic Pantalla LCD', 259990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Camara digital DSC-W320B', 123490, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Apple iPod shuffle', 1499990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Sony Notebook Z110', 37990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Hewlett Packard Multifuncional F2280', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Bianchi Bicicleta Aro 26', 69990, NOW());
INSERT INTO productos (nombre, precio, create_at) VALUES('Mica Comoda 5 Cajones', 299990, NOW());

/* Creamos algunas facturas */
INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura equipos de oficina', null, 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 1);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(2, 1, 4);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 5);
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(1, 1, 7);

INSERT INTO facturas (descripcion, observacion, cliente_id, create_at) VALUES('Factura Bicicleta', 'Alguna nota importante!', 1, NOW());
INSERT INTO facturas_items (cantidad, factura_id, producto_id) VALUES(3, 2, 6);

INSERT INTO users(username, password) VALUES('admin', '$2a$10$dsTo5h431M3Xff6aLdCkK.N.FgKw84dGuELrSwm.BZtcQd/TpgpB.');
INSERT INTO users(username,password) VALUES('sergio', '$2a$10$jVCrQDvxjgreny4d45CUGOOXkDs3qQrQHd5LFSklVT.dPcZYNieK6');

INSERT INTO authorities(user_id, authority) VALUES(1, 'ROLE_USER');
INSERT INTO authorities(user_id, authority) VALUES(1, 'ROLE_ADMIN');
INSERT INTO authorities(user_id, authority) VALUES(2, 'ROLE_USER');