CREATE TABLE team (
    team_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    version BIGINT NOT NULL
);

CREATE TABLE player (
    player_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    team_id INTEGER REFERENCES team(team_id) ON DELETE CASCADE,
    version BIGINT NOT NULL
);

CREATE SEQUENCE player_stats_id_seq
    START WITH 1
    INCREMENT BY 20
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE player_stats (
    player_stats_id SERIAL PRIMARY KEY,
    player_id INTEGER REFERENCES player(player_id) ON DELETE CASCADE,
    season INTEGER NOT NULL CHECK (season >= 1949),
    points INT NOT NULL CHECK (points >= 0),
    rebounds INT NOT NULL CHECK (rebounds >= 0),
    assists INT NOT NULL CHECK (assists >= 0),
    steals INT NOT NULL CHECK (steals >= 0),
    blocks INT NOT NULL CHECK (blocks >= 0),
    fouls INT NOT NULL CHECK (fouls >= 0 AND fouls <= 6),
    turnovers INT NOT NULL CHECK (turnovers >= 0),
    minutes_played FLOAT NOT NULL CHECK (minutes_played >= 0 AND minutes_played <= 48.0),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    version BIGINT NOT NULL
);

CREATE INDEX idx_player_team_id ON player(team_id);
CREATE INDEX idx_playerstats_player_id ON player_stats(player_id);