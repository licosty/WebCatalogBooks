DROP TABLE IF EXISTS book;

CREATE TABLE book
(
    id        INT          NOT NULL PRIMARY KEY AUTO_INCREMENT,
    author    VARCHAR(50)  NOT NULL,
    title     VARCHAR(100) NOT NULL,
    year      INT          NOT NULL,
    publisher VARCHAR(50)  NOT NULL,
    isbn      VARCHAR(20),
    pages     INT
);

INSERT INTO book (author, title, year, publisher, isbn, pages)
VALUES ('Достоевский Ф.М.', 'Идиот', 1869, 'Эксмо', '978-5-04-113339-9', 640),
       ('Шилдт Герберт', 'Java. Полное руководство', 2019, 'Альфа-книга', '978-5-6040043-6-4', 1488),
       ('Масао К.', 'Самурай без меча', 2021, 'Попурри, ООО', '978-985-15-4853-4', 208);