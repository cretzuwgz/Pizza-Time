TRUNCATE TABLE `a6871343_pizza`.`tip_produs`;
INSERT INTO `a6871343_pizza`.`tip_produs` (`id_tip`,`nume_tip`,`link_poza`)
VALUES 
('1', 'Antreuri', 'http://pizzatime.webege.com/poze/tipuri/antreuri.png'),
('2', 'Paste', 'http://pizzatime.webege.com/poze/tipuri/paste.png'),
('3', 'Pizza', 'http://pizzatime.webege.com/poze/tipuri/pizza.png'),
('4', 'Desert', 'http://pizzatime.webege.com/poze/tipuri/desert.png'),
('5', 'Salate', 'http://pizzatime.webege.com/poze/tipuri/salate.png'),
('6', 'Sosuri', 'http://pizzatime.webege.com/poze/tipuri/sosuri.png'),
('7', 'Bauturi', 'http://pizzatime.webege.com/poze/tipuri/bauturi.png');

TRUNCATE TABLE `a6871343_pizza`.`produs`;
INSERT INTO `a6871343_pizza`.`produs` (`id_prod`,`nume_prod`,`pret_prod`,`link_poza`,`id_tip`)
VALUES
('101','Paine cu branza, unt si usturoi','6','http://pizzatime.webege.com/poze/antreuri/paine-cu-branza-unt-aroma-de-usturoi.jpg','1'),
('102','Bruschete','9','http://pizzatime.webege.com/poze/antreuri/bruschette.jpg','1'),
('103','Quesadilla cu sunca si ciuperci','12','http://pizzatime.webege.com/poze/antreuri/quesadilla-cu-sunca-ciuperci-si-mozzarella.jpg','1'),
('104','Mini Calzone Meat Lovers','9','http://pizzatime.webege.com/poze/antreuri/meat-lovers.jpg','1'),
('105','Mini Calzone Cheese Lovers','9','http://pizzatime.webege.com/poze/antreuri/cheese-lovers.jpg','1'),
('106','Aripioare de pui','12','http://pizzatime.webege.com/poze/antreuri/aripioare-de-pui-8-aripioare.jpg','1'),
('107','Cartofi copti','7','http://pizzatime.webege.com/poze/antreuri/cartofi-copti-cu-un-sos.jpg','1'),
('108','Cartofi copti cu branza','10','http://pizzatime.webege.com/poze/antreuri/cartofi-copti-cu-branza.jpg','1'),
('109','Ciuperci la cuptor','10','http://pizzatime.webege.com/poze/antreuri/ciuperci21.jpg','1'),
('110','Platou combinat','20','http://pizzatime.webege.com/poze/antreuri/platou-combinat.jpg','1'),
('111','Tuscani Carbonara cu ciuperci','22','http://pizzatime.webege.com/poze/paste/tuscani-carbonara.jpg','2'),
('112','Tuscani quattro formaggi','22','http://pizzatime.webege.com/poze/paste/tuscani_quattro_formaggi_gratinate.jpg','2'),
('113','Tuscani Alfredo','22','http://pizzatime.webege.com/poze/paste/tuscani-alfredo-gratinate.jpg','2'),
('114','Tuscani marinara','22','http://pizzatime.webege.com/poze/paste/tuscani-marinara-gratinate.jpg','2'),
('115','Lasagna','24','http://pizzatime.webege.com/poze/paste/lasagna.jpg','2'),
('116','Margherita','20','http://pizzatime.webege.com/poze/pizza/margherita.jpg','3'),
('117','Roma','28','http://pizzatime.webege.com/poze/pizza/roma.jpg','3'),
('118','Vegetariana','28','http://pizzatime.webege.com/poze/pizza/vegetariana.jpg','3'),
('119','Palermo','28','http://pizzatime.webege.com/poze/pizza/palermo.jpg','3'),
('120','Hawaiana','28','http://pizzatime.webege.com/poze/pizza/hawaiana.jpg','3'),
('121','Picanta','32','http://pizzatime.webege.com/poze/pizza/picanta.jpg','3'),
('122','Europeana','32','http://pizzatime.webege.com/poze/pizza/europeana.jpg','3'),
('123','Pepperoni','32','http://pizzatime.webege.com/poze/pizza/peperoni.jpg','3'),
('124','California','32','http://pizzatime.webege.com/poze/pizza/california.jpg','3'),
('125','Suprema','32','http://pizzatime.webege.com/poze/pizza/suprema.jpg','3'),
('126','Montana','32','http://pizzatime.webege.com/poze/pizza/montana.jpg','3'),
('127','Taraneasca','32','http://pizzatime.webege.com/poze/pizza/taraneasca.jpg','3'),
('128','Quattro Formaggi','37','http://pizzatime.webege.com/poze/pizza/quattro-formaggi.jpg','3'),
('129','Super suprema','37','http://pizzatime.webege.com/poze/pizza/super-suprema.jpg','3'),
('130','Dolce Vita','10','http://pizzatime.webege.com/poze/desert/dolce-vita.jpg','4'),
('131','Scortisorele','8','http://pizzatime.webege.com/poze/desert/scortisorele.jpg','4'),
('132','Brownie','11','http://pizzatime.webege.com/poze/desert/brownie.jpg','4'),
('133','Tiramisu','9','http://pizzatime.webege.com/poze/desert/tiramisu.jpg','4'),
('134','Profiteroles','9','http://pizzatime.webege.com/poze/desert/profiteroles.jpg','4'),
('135','Vegi','10','http://pizzatime.webege.com/poze/salate/vegi.jpg','5'),
('136','Greceasca','15','http://pizzatime.webege.com/poze/salate/greceasca.jpg','5'),
('137','Caesar','17','http://pizzatime.webege.com/poze/salate/caesar.jpg','5'),
('138','Iaurt','3','http://pizzatime.webege.com/poze/sosuri/sos-iaurt.jpg','6'),
('139','Usturoi','3','http://pizzatime.webege.com/poze/sosuri/sos-usturoi.jpg','6'),
('140','Ketchup dulce','3','http://pizzatime.webege.com/poze/sosuri/ketchup-dulce.jpg','6'),
('141','Sos Caesar','3','http://pizzatime.webege.com/poze/sosuri/sos-caesar.jpg','6'),
('142','Ketchup iute','3','http://pizzatime.webege.com/poze/sosuri/ketchup-iute.jpg','6'),
('143','Vinaigrette','3','http://pizzatime.webege.com/poze/sosuri/sos-vinaigrette.jpg','6'),
('144','Salsa','3','http://pizzatime.webege.com/poze/sosuri/sos-salsa.jpg','6'),
('145','Dorna plata','3','http://pizzatime.webege.com/poze/bauturi/apa.jpg','7'),
('146','Dorna minerala','3','http://pizzatime.webege.com/poze/bauturi/apa.jpg','7'),
('147','Nestea piersici','5','http://pizzatime.webege.com/poze/bauturi/nestea.jpg','7'),
('148','Nestea lamaie','5','http://pizzatime.webege.com/poze/bauturi/nestea.jpg','7'),
('149','Nestea mango','5','http://pizzatime.webege.com/poze/bauturi/nestea.jpg','7'),
('150','Coca-Cola','6','http://pizzatime.webege.com/poze/bauturi/sucuri.jpg','7'),
('151','Coca-Cola Zero','6','http://pizzatime.webege.com/poze/bauturi/sucuri.jpg','7'),
('152','Fanta Orange','6','http://pizzatime.webege.com/poze/bauturi/sucuri.jpg','7'),
('153','Sprite','6','http://pizzatime.webege.com/poze/bauturi/sucuri.jpg','7'),
('154','Ursus Cut','5','http://pizzatime.webege.com/poze/bauturi/bere.jpg','7'),
('155','Ursus fara alcool','6','http://pizzatime.webege.com/poze/bauturi/bere.jpg','7'),
('156','Tuborg Cut','6','http://pizzatime.webege.com/poze/bauturi/bere.jpg','7'),
('157','Carlsberg Cut','7','http://pizzatime.webege.com/poze/bauturi/bere.jpg','7');

TRUNCATE TABLE `a6871343_pizza`.`oferta`;
INSERT INTO `a6871343_pizza`.`oferta` (`id_meniu`,`nume_meniu`,`pret`,`descriere`,`link_poza`)
VALUES
('1','Meniu Junior','15','1 Pizza Margherita + jucarie + Desert-Scortisorele','http://pizzatime.webege.com/poze/oferte/junior.jpg'),
('2','2 Pizza Roma','36','2 pizza Roma (Sos tomat, branza Mozzarella, sunca, ciuperci, masline)','http://pizzatime.webege.com/poze/oferte/roma.jpg'),
('3','Meniu Combo','41','Pizza Suprema + 1 portie Bruschette + Ketchup Dulce + Coca-Cola (4*0.5L)','http://pizzatime.webege.com/poze/oferte/combo.jpg');

TRUNCATE TABLE `a6871343_pizza`.`produs_din_oferta`;
INSERT INTO `a6871343_pizza`.`produs_din_oferta` (`id_prod`,`id_oferta`,`cantitate`)
VALUES
('116','1','1'),
('131','1','1'),
('117','2','2'),
('125','3','1'),
('102','3','1'),
('140','3','1'),
('150','3','4');