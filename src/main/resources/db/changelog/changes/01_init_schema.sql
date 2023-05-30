CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          first_name VARCHAR NOT NULL,
                          last_name VARCHAR NOT NULL,
                          address VARCHAR NOT NULL,
                          phone_number VARCHAR UNIQUE
);
