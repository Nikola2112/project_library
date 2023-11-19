CREATE TABLE book (
                      book_id SERIAL PRIMARY KEY,
                      book_name VARCHAR(255) NOT NULL,
                      book_author VARCHAR(255) NOT NULL,
                      book_year_of_publish BIGINT,
                      borrower_id BIGINT,
                      CONSTRAINT fk_borrower FOREIGN KEY (borrower_id) REFERENCES person(person_id) ON DELETE SET NULL
);
