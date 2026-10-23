CREATE TABLE IF NOT EXISTS STUDENTS (
    id      INT NOT NULL AUTO_INCREMENT,
    name    VARCHAR(15),
    surname VARCHAR(25),
    faculty VARCHAR(20),
    age     INT,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS DISCIPLINES (
    id       INT NOT NULL AUTO_INCREMENT,
    name     VARCHAR(50) NOT NULL,
    teacher  VARCHAR(50),
    hours    INT,
    semester INT,
    PRIMARY KEY (id)
);
