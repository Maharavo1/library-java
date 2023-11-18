--Scripts
CREATE TABLE IF NOT EXISTS subscribers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    reference VARCHAR(200)
);

--Insert
INSERT INTO subscribers (id, name, reference)
 VALUES
    (1, 'John', 'REF123'),
    ( 2,'Smith', 'REF456'),
    ( 3,'Alice ', 'REF789');