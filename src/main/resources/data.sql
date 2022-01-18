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

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 11, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 13, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 9, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 7, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 12, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 15, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 9, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 12, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Savoillan', 6, (SELECT id FROM cities WHERE name = 'Brantes'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Léger-du-Ventoux', 9, (SELECT id FROM cities WHERE name = 'Brantes'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 7, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 15, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 8, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 12, (SELECT id FROM cities WHERE name = 'Buisson'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 6, (SELECT id FROM cities WHERE name = 'Buisson'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 12, (SELECT id FROM cities WHERE name = 'Cairanne'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 9, (SELECT id FROM cities WHERE name = 'Cairanne'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 16, (SELECT id FROM cities WHERE name = 'Cairanne'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 25, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 12, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 10, (SELECT id FROM cities WHERE name = 'Crestet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 12, (SELECT id FROM cities WHERE name = 'Crestet'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 14, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 10, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 10, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 9, (SELECT id FROM cities WHERE name = 'Entrechaux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 14, (SELECT id FROM cities WHERE name = 'Entrechaux'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 9, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 14, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 14, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 6, (SELECT id FROM cities WHERE name = 'Faucon'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 5, (SELECT id FROM cities WHERE name = 'Faucon'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Léger-du-Ventoux', 12, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 16, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 6, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 6, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 5, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 14, (SELECT id FROM cities WHERE name = 'Puyméras'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 16, (SELECT id FROM cities WHERE name = 'Puyméras'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 14, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 6, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 16, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 10, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Cairanne', 9, (SELECT id FROM cities WHERE name = 'Rasteau'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Roman-de-Malegarde', 14, (SELECT id FROM cities WHERE name = 'Rasteau'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 8, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 11, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 12, (SELECT id FROM cities WHERE name = 'Roaix'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 7, (SELECT id FROM cities WHERE name = 'Roaix'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 12, (SELECT id FROM cities WHERE name = 'Sablet'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Séguret', 9, (SELECT id FROM cities WHERE name = 'Sablet'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Brantes', 9, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Mollans-sur-Ouvèze', 16, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 9, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 10, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 12, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 9, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 7, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Faucon', 7, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Entrechaux', 18, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Marcellin-lès-Vaison', 9, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 12, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Villedieu', 18, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 6, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 13, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Cairanne', 10, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Brantes', 6, (SELECT id FROM cities WHERE name = 'Savoillan'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 19, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Crestet', 23, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Sablet', 9, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Rasteau', 14, (SELECT id FROM cities WHERE name = 'Séguret'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 10, (SELECT id FROM cities WHERE name = 'Séguret'));

INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Puyméras', 16, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Saint-Romain-en-Viennois', 15, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Vaison-la-Romaine', 13, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Roaix', 8, (SELECT id FROM cities WHERE name = 'Villedieu'));
INSERT INTO itineraries (name_of_direction, duration_of_travel, city_id) VALUES ('Buisson', 7, (SELECT id FROM cities WHERE name = 'Villedieu'));

INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'emmanuelm_ccvv@prefecture.gouv.fr', 'Système', 'EmmanuelM', 'root', 0, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'francoish_ccvv@prefecture.gouv.fr', 'Système', 'FrançoisH', 'root', 0, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'nicolass_ccvv@prefecture.gouv.fr', 'Système', 'NicolasS', 'root', 1, (SELECT id FROM cities WHERE name = 'Saint-Marcellin-lès-Vaison'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'jacquesc_ccvv@prefecture.gouv.fr', 'Système', 'JacquesC', 'root', 1, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'francoism_ccvv@prefecture.gouv.fr', 'Système', 'FrançoisM', 'root', 2, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'valeryge_ccvv@prefecture.gouv.fr', 'Système', 'ValéryGE', 'root', 2, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'alainp_ccvv@prefecture.gouv.fr', 'Système', 'AlainP', 'root', 0, (SELECT id FROM cities WHERE name = 'Saint-Roman-de-Malegarde'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'georgesp_ccvv@prefecture.gouv.fr', 'Système', 'GeorgesP', 'root', 0, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'alainp_ccvv@prefecture.gouv.fr', 'Système', 'AlainP', 'root', 1, (SELECT id FROM cities WHERE name = 'Mollans-sur-Ouvèze'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'charlesdg_ccvv@prefecture.gouv.fr', 'Système', 'CharlesDG', 'root', 1, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'renec_ccvv@prefecture.gouv.fr', 'Système', 'RenéC', 'root', 2, (SELECT id FROM cities WHERE name = 'Vaison-la-Romaine'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'vincenta_ccvv@prefecture.gouv.fr', 'Système', 'VincentA', 'root', 2, (SELECT id FROM cities WHERE name = 'Saint-Léger-du-Ventoux'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'albertl_ccvv@prefecture.gouv.fr', 'Système', 'AlbertL', 'root', 0, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));
INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', true, 'albertl_ccvv@prefecture.gouv.fr', 'Système', 'user', '$2a$12$lWBJfRHMMNjqS4sS.6owhOoa6WDD6jVCpCu8yvjEKW4kgaT9JB/2y', 0, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

INSERT INTO users (created_by, exist, mail, modified_by, name, password, user_role, city_id) VALUES ('Système', false, 'test@test.com', 'Système', 'test', 'root', 2, (SELECT id FROM cities WHERE name = 'Saint-Romain-en-Viennois'));

--INSERT INTO reasons_rdv (category,duration_minutes,email,link_doc,name) VALUES( 0, 45  , "user5@gmail.com" ,"www.link5.com" ,  "resident card" )