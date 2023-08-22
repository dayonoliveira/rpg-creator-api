CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    nickname VARCHAR NOT NULL UNIQUE,
    email VARCHAR NOT NULL UNIQUE,
    password VARCHAR NOT NULL,
    bio VARCHAR,
    birth_date DATE,
    photo BYTEA
);

CREATE TABLE IF NOT EXISTS rpgs (
    rpg_id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL UNIQUE,
    based_at VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    decision_method VARCHAR NOT NULL,
    min_players INT NOT NULL,
    max_players INT,
    created_by BIGINT,
    is_public BOOLEAN NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users (user_id)
);

CREATE TABLE IF NOT EXISTS games (
    game_id SERIAL PRIMARY KEY,
    created_by BIGINT NOT NULL,
    new_owner BIGINT,
    has_ended BOOLEAN NOT NULL,
    related_at_rpg BIGINT NOT NULL,
    access_code VARCHAR NOT NULL,
    FOREIGN KEY (created_by) REFERENCES users (user_id),
    FOREIGN KEY (new_owner) REFERENCES users (user_id),
    FOREIGN KEY (related_at_rpg) REFERENCES rpgs (rpg_id)
);

CREATE TABLE IF NOT EXISTS sessions (
    session_id SERIAL PRIMARY KEY,
    started_at TIMESTAMP NOT NULL,
    ended_at TIMESTAMP,
    leaded_by BIGINT NOT NULL,
    related_at_game BIGINT NOT NULL,
    step INT NOT NULL,
    is_final_game_session BOOLEAN NOT NULL,
    is_live BOOLEAN NOT NULL,
    FOREIGN KEY (leaded_by) REFERENCES users (user_id),
    FOREIGN KEY (related_at_game) REFERENCES games (game_id)
);

CREATE TABLE IF NOT EXISTS games_users (
    user_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (game_id) REFERENCES games (game_id)
);

CREATE TABLE IF NOT EXISTS favorites (
    user_id BIGINT NOT NULL,
    rpg_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (rpg_id) REFERENCES rpgs (rpg_id)
);

CREATE TABLE IF NOT EXISTS sheets (
    user_id BIGINT NOT NULL,
    sheet BYTEA NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (user_id)
);
