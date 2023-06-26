CREATE TABLE IF NOT EXISTS category
(id SERIAL NOT NULL,
name character VARCHAR (64) NOT NULL,
dt_create timestamp (3) without time zone,
dt_update timestamp (3) without time zone,
CONSTRAINT category_pkey PRIMARY KEY (id))


