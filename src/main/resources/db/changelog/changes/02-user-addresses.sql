CREATE TABLE addresses (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    postal_code VARCHAR(255),
    country VARCHAR(255),
    city VARCHAR(255),
    street VARCHAR(255),
    number VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);