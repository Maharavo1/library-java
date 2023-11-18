--Srcipt
CREATE TABLE IF NOT EXISTS author (
    id SERIAL PRIMARY KEY,
    authorName VARCHAR(200),
    sex CHAR(1) CHECK (sex IN ('M', 'F'))
);

-- Insert
INSERT INTO author (authorName, sex)
VALUES
    ('Faly', 'M'),
    ('Ranto', 'F'),
    ('Jesse', 'M')

