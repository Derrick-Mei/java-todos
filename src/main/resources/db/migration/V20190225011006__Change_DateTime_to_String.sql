PRAGMA foreign_keys = 0;

CREATE TABLE sqlitestudio_temp_table AS SELECT *
                                          FROM todo;

DROP TABLE todo;

CREATE TABLE todo (
    todoid      INTEGER PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    description STRING  NOT NULL,
    datestarted STRING,
    completed   BOOLEAN,
    userid      INTEGER REFERENCES users (userid)
                        NOT NULL
);

INSERT INTO todo (
                     todoid,
                     description,
                     datestarted,
                     completed,
                     userid
                 )
                 SELECT todoid,
                        description,
                        datestarted,
                        completed,
                        userid
                   FROM sqlitestudio_temp_table;

DROP TABLE sqlitestudio_temp_table;

PRAGMA foreign_keys = 1;