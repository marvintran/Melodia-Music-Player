CREATE SCHEMA PUBLIC AUTHORIZATION DBA
CREATE MEMORY TABLE PUBLIC.ARTIST(ARTISTNAME VARCHAR(20) NOT NULL PRIMARY KEY)
CREATE MEMORY TABLE PUBLIC.ALBUM(ALBUMNAME VARCHAR(20) NOT NULL PRIMARY KEY)
CREATE MEMORY TABLE PUBLIC.SONG(SONGNAME VARCHAR(20), SONGTIME INTEGER NOT NULL, ARTISTNAME VARCHAR(20) NOT NULL, ALBUMNAME VARCHAR(20) NOT NULL, TRACKNUMBER INTEGER NOT NULL)
CREATE MEMORY TABLE PUBLIC.PLAYLIST(PLAYLISTNAME VARCHAR(20) NOT NULL PRIMARY KEY, SONGNAME VARCHAR(20) NOT NULL)
CREATE MEMORY TABLE PUBLIC.ACCOUNT(USERNAME VARCHAR(8) NOT NULL PRIMARY KEY,FULLNAME VARCHAR(20) NOT NULL, EMAIL VARCHAR(20) NOT NULL, PROFILE VARCHAR(20) NOT NULL)
CREATE USER SA PASSWORD DIGEST 'd41d8cd98f00b204e9800998ecf8427e'
ALTER USER SA SET LOCAL TRUE
CREATE SCHEMA PUBLIC AUTHORIZATION DBA
SET DATABASE DEFAULT INITIAL SCHEMA PUBLIC
GRANT DBA TO SA
SET WRITE_DELAY 20
SET FILES SCALE 32
SET SCHEMA PUBLIC






