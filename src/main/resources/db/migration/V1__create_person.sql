CREATE TABLE person (
                        person_id SERIAL PRIMARY KEY,
                        person_name VARCHAR(255) NOT NULL,
                        person_surname VARCHAR(255) NOT NULL,
                        person_year_of_birthday BIGINT
);
