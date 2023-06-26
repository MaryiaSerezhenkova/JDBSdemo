INSERT INTO category(
	name, dt_create, dt_update)
	VALUES ('Shampoo', now(), now()),
	('Soap', now(), now()),
	('Washing powder', now(), now());
	
	
	INSERT INTO product(
	name, price, category, dt_create, dt_update)
	VALUES ('Kaaral', 40.0, 1, now(), now()),
	('Dove', 5.0, 2, now(), now()),
	('BioMio', 35.0, 3, now(), now()),
	('Inebria', 50.0, 1, now(), now()),
	('Duru', 3.0, 2, now(), now()),
	('Persil', 20.0, 3, now(), now());
	
	
	INSERT INTO region(
	name, dt_create, dt_update)
	VALUES ('Minsk', now(), now()),
	('Brest', now(), now()),
	('Grodno', now(), now());
	
--	INSERT INTO product_region(
	product_id, region_id)
	VALUES (1, 1),
	(1, 2),
	(1, 3),
	(2, 1),
	(3, 2),
	(3, 3),
	(5, 1),
	(6, 2);