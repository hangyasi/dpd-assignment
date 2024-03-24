CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    birth_place VARCHAR(255) NOT NULL,
    mothers_name VARCHAR(255) NOT NULL,
    social_security_number CHAR(9) NOT NULL,
    tax_identification_number CHAR(10) NOT NULL,
    email_address VARCHAR(255) NOT NULL
);