CREATE SCHEMA IF NOT EXISTS devSchema;

SET search_path TO devSchema;

CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    username VARCHAR(255),
    mail VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS project (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE IF NOT EXISTS complexity (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    name VARCHAR(50) NOT NULL,
    project_id BIGINT NOT NULL,
    FOREIGN KEY (project_id) REFERENCES project (id)
);

CREATE TABLE IF NOT EXISTS resource (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    title VARCHAR(50) NOT NULL,
    link VARCHAR(255) NOT NULL,
    type VARCHAR(25),
    complexity_id BIGINT,
    FOREIGN KEY (complexity_id) REFERENCES complexity (id)
);

CREATE TABLE IF NOT EXISTS tags (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    name VARCHAR(255) NOT NULL,
    os VARCHAR(255) NOT NULL,
    description VARCHAR(75) NOT NULL
);

CREATE TABLE IF NOT EXISTS entry (
    id BIGSERIAL PRIMARY KEY,
    active_status BOOLEAN,
    date_created DATE,
    last_updated DATE,
    command VARCHAR(100) NOT NULL,
    response VARCHAR(100),
    hint VARCHAR(100),
    complexity_id BIGINT,
    FOREIGN KEY (complexity_id) REFERENCES complexity (id)
);

CREATE TABLE IF NOT EXISTS entry_tags (
    entry_id BIGINT,
    tag_id BIGINT,
    PRIMARY KEY (entry_id, tag_id),
    FOREIGN KEY (entry_id) REFERENCES entry (id),
    FOREIGN KEY (tag_id) REFERENCES tags (id)
);