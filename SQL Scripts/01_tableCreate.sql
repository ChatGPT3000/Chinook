CREATE TABLE Superhero (
    Id serial PRIMARY KEY,
    Name varchar(40),
    Alias varchar(50),
    Origin varchar(2000)
);

CREATE TABLE Assistant (
    Id serial PRIMARY KEY,
    Name varchar(40)
);

CREATE TABLE Power (
    Id serial PRIMARY KEY,
    Name varchar(40),
    Description varchar(200)
);