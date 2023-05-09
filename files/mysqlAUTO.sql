drop DATABASE if EXISTS productos;
create database productos;

use productos;

CREATE TABLE `instrumentos` (
  `codigo` varchar(15) NOT NULL,
  `marca` varchar(30) NOT NULL,
  `precio` decimal(7,2) NOT NULL,
  `descuento` decimal(5,2) NOT NULL,
  `tipo` varchar(15) NOT NULL,
  `color` varchar(30) NOT NULL,
  `teclas` varchar(3) NOT NULL,
  `conector` varchar(15) NOT NULL,
  `envio` varchar(20) GENERATED ALWAYS AS (if(`tipo` = 'PRIME','GRATIS','+15€')) VIRTUAL,
  `pvp` decimal(8,2) GENERATED ALWAYS AS (if(`tipo` = 'PRIME',`precio` - `precio` * `descuento` / 100,`precio` - `precio` * `descuento` / 100 + 15)) VIRTUAL
) ;

ALTER TABLE `instrumentos` ADD CONSTRAINT ch_dcto CHECK (`descuento` BETWEEN 0 AND 100);
ALTER TABLE `instrumentos` ADD CONSTRAINT ch_precio CHECK (`precio` >= 0);
ALTER TABLE `instrumentos` ADD CONSTRAINT ch_teclas CHECK (`teclas` > 0);

INSERT INTO `instrumentos` (`codigo`, `marca`, `precio`, `descuento`, `tipo`, `color`, `teclas`, `conector`) VALUES
('t01', 'DOS', '10.00', '10.00', 'PRIME', 'NEGRO', '61', 'USB'),
('t03', 'TES', '10.00', '1.00', 'PRIME', 'NEGRO', '12', 'USB'),
('t04', 'FENDER', '10.00', '1.00', 'PRIME', 'NEGRO', '61', 'USB'),
('t05', 'GIBSON', '500.00', '10.00', 'REGULAR', 'NEGRO', '61', 'USB'),
('t32', 'UNO', '10.00', '10.00', 'REGULAR', 'BLANCO', '61', 'MIDI'),
('t322', 'HELA', '15.00', '100.00', 'REGULAR', 'VERDE', '20', 'USB'),
('t4', 'PERL', '500.00', '10.00', 'REGULAR', 'ROJO', '84', 'MIDI'),
('t55', 'FENDER', '450.00', '10.00', 'PRIME', 'BLANCO', '61', 'USB'),
('t65', 'YAMAHA', '375.00', '25.00', 'REGULAR', 'NEGRO', '61', 'XFIND'),
('t7', 'PACIFIC', '1000.00', '10.00', 'REGULAR', 'ROJO', '81', 'USB'),
('t8', 'FENDERA', '1000.00', '10.00', 'PRIME', 'VERDE', '61', 'USB'),
('t9', 'TECH', '100.00', '10.00', 'PRIME', 'ROJO', '61', 'USB'),
('t93', 'TECH', '100.00', '1.00', 'PRIME', 'ROJO', '61', 'MIDI'),
('tz', 'KING', '500.00', '100.00', 'REGULAR', 'NEGRO', '61', 'MIDI');


ALTER TABLE `instrumentos`
  ADD PRIMARY KEY (`codigo`);
COMMIT;