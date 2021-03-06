INSERT INTO cities (name) VALUES ('Vaison-la-Romaine');
INSERT INTO cities (name) VALUES ('Brantes');
INSERT INTO cities (name) VALUES ('Buisson');
INSERT INTO cities (name) VALUES ('Cairanne');
INSERT INTO cities (name) VALUES ('Crestet');
INSERT INTO cities (name) VALUES ('Entrechaux');
INSERT INTO cities (name) VALUES ('Faucon');
INSERT INTO cities (name) VALUES ('Mollans-sur-Ouvèze');
INSERT INTO cities (name) VALUES ('Puyméras');
INSERT INTO cities (name) VALUES ('Rasteau');
INSERT INTO cities (name) VALUES ('Roaix');
INSERT INTO cities (name) VALUES ('Sablet');
INSERT INTO cities (name) VALUES ('Saint-Léger-du-Ventoux');
INSERT INTO cities (name) VALUES ('Saint-Marcellin-lès-Vaison');
INSERT INTO cities (name) VALUES ('Saint-Romain-en-Viennois');
INSERT INTO cities (name) VALUES ('Saint-Roman-de-Malegarde');
INSERT INTO cities (name) VALUES ('Savoillan');
INSERT INTO cities (name) VALUES ('Séguret');
INSERT INTO cities (name) VALUES ('Villedieu');

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 19, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 19, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 15, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 34, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 17, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 20, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 18, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 20, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Savoillan', 14, (SELECT id FROM cities WHERE name = 'Brantes'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Léger-du-Ventoux', 17, (SELECT id FROM cities WHERE name = 'Brantes'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 15, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 20, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 16, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 20, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 13, (SELECT id FROM cities WHERE name = 'Buisson'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 20, (SELECT id FROM cities WHERE name = 'Cairanne'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 16, (SELECT id FROM cities WHERE name = 'Cairanne'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 26, (SELECT id FROM cities WHERE name = 'Cairanne'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 27, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 17, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 16, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 17, (SELECT id FROM cities WHERE name = 'Crestet'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 18, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 16, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 17, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 17, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 21, (SELECT id FROM cities WHERE name = 'Entrechaux'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 16, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 18, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 19, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 14, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 13, (SELECT id FROM cities WHERE name = 'Faucon'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Léger-du-Ventoux', 22, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 16, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 16, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 13, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 13, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 19, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 25, (SELECT id FROM cities WHERE name = 'Puyméras'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 20, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 14, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 20, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 20, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Cairanne', 16, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 19, (SELECT id FROM cities WHERE name = 'Rasteau'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 16, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 18, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 15, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 14, (SELECT id FROM cities WHERE name = 'Roaix'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 22, (SELECT id FROM cities WHERE name = 'Sablet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 19, (SELECT id FROM cities WHERE name = 'Sablet'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Brantes', 17, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 22, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 32, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 17, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 16, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 34, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 13, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 14, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 22, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 32, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 15, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 22, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 13, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 19, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Cairanne', 20, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Brantes', 14, (SELECT id FROM cities WHERE name = 'Savoillan'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 20, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 27, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 20, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 20, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 15, (SELECT id FROM cities WHERE name = 'Séguret'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 25, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 22, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 19, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 16, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 15, (SELECT id FROM cities WHERE name = 'Villedieu'));

--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'emmanuelm_ccvv@prefecture.gouv.fr', 'Système', 'EmmanuelM', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'francoish_ccvv@prefecture.gouv.fr', 'Système', 'FrançoisH', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'nicolass_ccvv@prefecture.gouv.fr', 'Système', 'NicolasS', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 1, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'jacquesc_ccvv@prefecture.gouv.fr', 'Système', 'JacquesC', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 1, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'francoism_ccvv@prefecture.gouv.fr', 'Système', 'FrançoisM', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 2, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'valeryge_ccvv@prefecture.gouv.fr', 'Système', 'ValéryGE', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 2, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'alainp_ccvv@prefecture.gouv.fr', 'Système', 'AlainP2', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'georgesp_ccvv@prefecture.gouv.fr', 'Système', 'GeorgesP', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'alainp_ccvv@prefecture.gouv.fr', 'Système', 'AlainP1', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 1, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'charlesdg_ccvv@prefecture.gouv.fr', 'Système', 'CharlesDG', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 1, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'renec_ccvv@prefecture.gouv.fr', 'Système', 'RenéC', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 2, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'vincenta_ccvv@prefecture.gouv.fr', 'Système', 'VincentA', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 2, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'albertl_ccvv@prefecture.gouv.fr', 'Système', 'AlbertL', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'creativegirl@outlook.fr', 'Système', 's', '$2a$12$YZgY.r45Z/Pm2qcOahHqyeQrz.5LShGhfTBAmGmgg/aSMB.k/K1ZS', 0, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'creativegirl@outlook.fr', 'Système', 'a', '$2a$12$xLkXpMJ.OJqRucthmy4aiuhYpC5wzPG7IaECOJhqcYCothYKtFPby', 1, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'creativegirl@outlook.fr', 'Système', 'u', '$2a$12$cPyg10X..TZ14j1R8eaA9O9GmlKYoA/1EbIUt3wddTnC7vsDqFz.K', 2, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

--INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', false, 'test@test.com', 'Système', 'test', 'root', 2, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

--INSERT INTO reasons_rdv (category,duration_minutes,email,link_doc,name) VALUES( 0, 45  , "user5@gmail.com" ,"www.link5.com" ,  "resident card" );

insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (0, 20, 'www.kkkkk.com', 'suppression de permis', true);
insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (1, 30, 'www.link2.com', 'carte grise', true);
insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (0, 45, 'www.link3.com', 'permis de construire', true);
insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (1, 20, 'www.link4.com', 'carte d`identité', true);
insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (0, 45, 'www.link5.com', 'carte de résident', true);
insert into reasons_rdv (category, duration_minutes, link_doc, name, exist) values (1, 180, 'www.link6.com', 'rdv très long de 4h', true);

--insert into rdvs(date_and_time, first_name, name, path_duration, phone, status, id_city, id_reason) VALUES ('2022-01-01', 'test', 'test', 0, '99 99 99 99 99', 1, 1, 1);
--insert into rdvs(date_and_time, first_name, name, path_duration, phone, status, id_city, id_reason) VALUES ('2022-01-02', 'test', 'test', 0, '99 99 99 99 99', 1, 1, 1);

INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('HUGO', 'Victor', '51 42 33 24 15', 'hugov@gmail.com', false, '2022-03-25', '14:30', 0, (SELECT id FROM reasons_rdv WHERE name = 'permis de construire'), (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('DUMAS', 'Alexandre', '01 02 03 04 05', 'dumasa@gmail.com', false, '2022-03-21', '15:30', 0, (SELECT id FROM reasons_rdv WHERE name = 'carte grise'), (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('VERNE', 'Jules', '11 22 33 44 55', 'vernejr@gmail.com', false, '2022-03-25', '11:30', 0, (SELECT id FROM reasons_rdv WHERE name = 'permis de construire'), (SELECT id FROM cities WHERE name = 'Faucon'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('SARTRE', 'Jean-Paul', '61 62 63 64 65', 'sartrejp@gmail.com', false, '2022-03-18', '08:00', 0, (SELECT id FROM reasons_rdv WHERE name = 'rdv très long de 4h'), (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('DE BEAUVOIR', 'Simone', '71 72 73 74 75', 'debeauvoirs@gmail.com', false, '2022-03-18', '14:00', 0, (SELECT id FROM reasons_rdv WHERE name = 'rdv très long de 4h'), (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('VOLTAIRE', 'François-Marie', '11 22 33 44 55', 'voltairefm@gmail.com', false, '2021-11-12', '14:30', 1, (SELECT id FROM reasons_rdv WHERE name = 'permis de construire'), (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('DE BALZAC', 'Honoré', '11 22 33 44 55', 'debalzach@gmail.com', false, '2021-12-10', '10:30', 1, (SELECT id FROM reasons_rdv WHERE name = 'carte grise'), (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('STENDHAL', 'Henri', '11 22 33 44 55', 'stendhalh@gmail.com', false, '2021-12-10', '14:30', 1, (SELECT id FROM reasons_rdv WHERE name = 'carte grise'), (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('PROUST', 'Marcel', '11 22 33 44 55', 'proustm@gmail.com', false, '2021-10-11', '09:30', 1, (SELECT id FROM reasons_rdv WHERE name = 'carte de résident'), (SELECT id FROM cities WHERE name = 'Roaix'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('CAMUS', 'Albert', '21 22 23 24 25', 'camusa@gmail.com', false, '2022-01-31', '15:30', 2, (SELECT id FROM reasons_rdv WHERE name = 'permis de construire'), (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('ZOLA', 'Emile', '23 32 33 34 35', 'zolae@gmail.com', false, '2022-01-21', '16:00', 2, (SELECT id FROM reasons_rdv WHERE name = 'carte d`identité'), (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'),'Système', 'Système');
INSERT INTO rdvs(name, first_name, phone_number, mail, no_mail, date, time, status, rdv_reason_id, city_id, created_by, modified_by) VALUES ('DE SAINT-EXUPERY', 'Antoine', '41 42 43 44 45', 'de.saint-exuperya@gmail.com', false, '2022-01-25', '10:30', 2, (SELECT id FROM reasons_rdv WHERE name = 'suppression de permis'), (SELECT id FROM cities WHERE name = 'Faucon'),'Système', 'Système');


INSERT INTO unavailable_days(date,created_by,created_at) VALUES ('2022-04-05','Système','2022-04-05 10:20');