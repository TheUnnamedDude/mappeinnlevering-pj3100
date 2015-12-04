CREATE TABLE IF NOT EXISTS album_info(
  artist CHAR(255) NOT NULL,
  title CHAR(255) NOT NULL,
  track INT,
  released INT,
  genre CHAR(255)
);

INSERT INTO album_info VALUES('Pink Floyd', 'Shine on you crazy diamond', 3, 1975, 'Rock');
INSERT INTO album_info VALUES('Pink Floyd', 'The Fletcher Memorial Home', 4, 1982, 'Rock');
INSERT INTO album_info VALUES('Pink Floyd', 'Wish You Were Here', 3, 1975, 'Rock');
# Er ikke så god på musikk så informasjonen som er her er ikke riktig.
INSERT INTO album_info VALUES('Mumble', 'Downtime Jazz', 3, 2013, 'Jazz');
INSERT INTO album_info VALUES('The cowboy', 'Someone stole my cow', 3, 1973, 'Country');
INSERT INTO album_info VALUES('Bob Marley', 'No Woman, No Cry', 1, 1975, 'Reggae');
INSERT INTO album_info VALUES('Alf Prøysen', 'Steinrøysa neri bakken', 1, 1948, 'Viser');