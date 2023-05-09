create database productos;

use productos;

create table instrumentos(
codigo varchar(15) primary key,
marca varchar(30) not null,
precio numeric(8,2) not null,
descuento numeric(5,2) not null,
tipo varchar(15) not null,
color varchar(30) not null,
teclas varchar(3) not null,
conector varchar(15) not null,
envio varchar (20) as (if (tipo = 'PRIME', 'GRATIS', '+15€')),
pvp numeric (8,2) as (if (tipo = 'PRIME', (precio - (precio*descuento)/100) , (precio - (precio*descuento)/100) + 15))
);

alter table instrumentos add constraint ch_dcto check (descuento between 0 and 100);
alter table instrumentos add constraint ch_precio check (precio >= 0);
alter table instrumentos add constraint ch_teclas check (teclas > 0);


