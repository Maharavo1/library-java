--Script
CREATE TABLE IF NOT EXISTS "book" (
    id SERIAL PRIMARY KEY,
    type VARCHAR(200),
    bookName VARCHAR(200),
    pageNumbers INTEGER,
    topic Topic,
    releaseDate DATE,
    authorId INT REFERENCES author(id),
    status VARCHAR(200) CHECK (status IN ('AVAILABLE', 'BORROWED'))
);

--Insert
INSERT INTO book (type, bookName, pageNumbers, topic, releaseDate, authorId, status)
VALUES
    ('Fiction', 'Book1', 300, 'ROMANCE', '2023-01-01', 1, 'AVAILABLE'),
    ('Non-Fiction', 'Book2', 250, 'COMEDY', '2023-02-01', 2, 'BORROWED'),
    ('Sci-Fi', 'Book3', 400, 'OTHER', '2023-03-01', 3, 'AVAILABLE');

