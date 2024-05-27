INSERT INTO team (name, version) VALUES ('Team A', 0);
INSERT INTO team (name, version) VALUES ('Team B', 0);
INSERT INTO team (name, version) VALUES ('Team C', 0);

INSERT INTO player (name, team_id, version) VALUES ('Player 1', 1, 0);
INSERT INTO player (name, team_id, version) VALUES ('Player 2', 1, 0);
INSERT INTO player (name, team_id, version) VALUES ('Player 3', 2, 0);
INSERT INTO player (name, team_id, version) VALUES ('Player 4', 2, 0);
INSERT INTO player (name, team_id, version) VALUES ('Player 5', 3, 0);
INSERT INTO player (name, team_id, version) VALUES ('Player 6', 3, 0);

INSERT INTO player_stats (player_stats_id, player_id, season, points, rebounds, assists, steals, blocks, fouls, turnovers, minutes_played, version)
VALUES
    (nextval('player_stats_id_seq'), 1, 2023, 10, 5, 3, 2, 1, 2, 1, 30, 0),
    (nextval('player_stats_id_seq'), 2, 2023, 15, 7, 4, 1, 2, 3, 2, 35, 0),
    (nextval('player_stats_id_seq'), 3, 2023, 20, 10, 5, 0, 1, 1, 1, 40, 0),
    (nextval('player_stats_id_seq'), 4, 2023, 5, 3, 2, 1, 0, 0, 1, 20, 0),
    (nextval('player_stats_id_seq'), 5, 2023, 12, 6, 4, 2, 1, 2, 2, 30, 0),
    (nextval('player_stats_id_seq'), 6, 2023, 18, 9, 7, 1, 2, 3, 3, 38, 0),
    (nextval('player_stats_id_seq'), 1, 2022, 8, 4, 3, 1, 0, 1, 1, 25, 0),
    (nextval('player_stats_id_seq'), 2, 2023, 20, 10, 6, 3, 2, 4, 2, 40, 0),
    (nextval('player_stats_id_seq'), 5, 2023, 10, 5, 4, 2, 1, 2, 1, 30, 0),
    (nextval('player_stats_id_seq'), 6, 2022, 25, 12, 8, 2, 3, 3, 2, 45, 0);