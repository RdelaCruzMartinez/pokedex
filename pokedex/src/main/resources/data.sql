DROP TABLE IF EXISTS POKEMON;

CREATE TABLE POKEMON (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  type_one VARCHAR(250) NOT NULL,
  type_two VARCHAR(250),
  hp NUMBER NOT NULL,
  generation NUMBER,
  legendary Boolean
);

INSERT INTO POKEMON (name, type_one, type_two, hp, generation, legendary) VALUES
  ('Bulbasaur', 'Grass', 'Poison', 45, 1, false),
  ('Charmander', 'Fire', null, 39, 1, false),
  ('Caterpie', 'Bug', null, 45, 1, false),
  ('Rattata', 'Normal', null, 30, 1, false),
  ('Mewtwo', 'Psychic', null, 110, 1, true),
  ('Blastoise', 'Water', null, 79, 1, false);