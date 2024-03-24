CREATE TABLE phone_numbers (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    phone_number VARCHAR(255),
    FOREIGN KEY (user_id) REFERENCES users(id)
);