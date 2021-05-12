DROP TABLE IF EXISTS posts;
CREATE TABLE posts
(
    id         varchar(255) not null
        constraint del_payment_type_unit_pkey
            primary key,
    title      varchar(255),
    body       varchar(255),
    user_id    bigint,
    updated_at  timestamp,
    is_deleted boolean
);

INSERT INTO posts (id, title, body, user_id, updated_at, is_deleted)
VALUES (1, 'Test', 'Lorem ipsum', 1, null, false);
INSERT INTO posts (id, title, body, user_id, updated_at, is_deleted)
VALUES (2, 'Testing', 'Ipsum ipsum', 1, null, false);
