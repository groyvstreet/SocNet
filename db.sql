CREATE TABLE roles
(
    id UUID PRIMARY KEY,
    name VARCHAR2(100) NOT NULL
);
CREATE TABLE users
(
    id UUID PRIMARY KEY,
    email text NOT NULL,
    password text NOT NULL,
    first_name VARCHAR2(100) NOT NULL,
    last_name VARCHAR2(100) NOT NULL,
    birth_date DATE NOT NULL,
    image text NOT NULL,
    role_id UUID REFERENCES roles(id) ON DELETE CASCADE
);
CREATE TABLE chats
(
    id UUID PRIMARY KEY,
    name text NOT NULL
);
CREATE TABLE chat_users
(
    chat_id UUID REFERENCES chats(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE messages
(
    id UUID PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    text text NOT NULL,
    chat_id UUID REFERENCES chats(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE photos
(
    id UUID PRIMARY KEY,
    source text NOT NULL,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE posts
(
    id UUID PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    text text NOT NULL,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE
);
CREATE TABLE comments
(
    id UUID PRIMARY KEY,
    date_time TIMESTAMP NOT NULL,
    text text NOT NULL,
    boolean is_root NOT NULL,
    post_id UUID REFERENCES posts(id) ON DELETE CASCADE,
    user_id UUID REFERENCES users(id) ON DELETE CASCADE,
    comment_id UUID NULL REFERENCES comments(id) ON DELETE CASCADE
);
