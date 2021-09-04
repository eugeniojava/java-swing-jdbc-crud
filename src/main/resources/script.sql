CREATE TABLE customers
(
    id      BIGSERIAL PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    age     INT          NOT NULL,
    segment VARCHAR(255) NOT NULL
);

CREATE TABLE employees
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age  INT          NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE products
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price    NUMERIC      NOT NULL,
    quantity INT          NOT NULL
);

INSERT INTO customers(name, age, segment)
VALUES ('Eric', 25, 'Financial'),
       ('Ericksen', 30, 'Marketing'),
       ('John', 35, 'Sales'),
       ('Johnatan', 20, 'Engineering'),
       ('Mary', 40, 'Marketing');

INSERT INTO employees(name, age, role)
VALUES ('Pietro', 30, 'Assistant'),
       ('August', 40, 'Engineer'),
       ('Victor', 50, 'Intern'),
       ('Augustino', 25, 'Engineer'),
       ('Victoria', 18, 'Intern');

INSERT INTO products(name, price, quantity)
VALUES ('Computer', 99.99, 100),
       ('Bicycle', 299.99, 200),
       ('Camera', 399.99, 300),
       ('Mobile phone', 460.00, 400),
       ('Laptop', 999.99, 500);
