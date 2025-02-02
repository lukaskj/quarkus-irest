CREATE TABLE location (
   id int8 NOT null generated by default as IDENTITY,
   latitude float8 NULL,
   longitude float8 null,
   constraint location_pkey primary key (id)
);

CREATE TABLE restaurant (
   id int8 NOT null generated by default as IDENTITY,
   name varchar(50) NULL,
   location_id int8 not null,
   constraint restaurant_pkey primary key (id)
);

alter table restaurant add constraint res_loc foreign key (location_id) references location(id);


CREATE TABLE meal (
   id int8 NOT null generated by default as IDENTITY,
   name varchar(50) NULL,
   description varchar(250) NULL,
   price numeric(19,2) not null,
   restaurant_id int8 not null,
   constraint meal_pkey primary key (id)
);

alter table meal add constraint meal_res foreign key (restaurant_id) references restaurant(id);

CREATE TABLE meal_client (
   meal_id int8 not null,
   client varchar(200)
);


insert into location (id, latitude, longitude) values (1000, -15.8177, -47.8369);

insert into restaurant (id, location_id, name) values (999, 1000, 'Manguai');

insert into meal (id, name, description, restaurant_id, price)
values (9997, 'Peixe frito', 'Agulhinha frita, excelente com cerveja', 999, 99.99);
