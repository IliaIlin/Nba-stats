# Nba Stats Application

## How to build/start the app
If first time, run
`docker-compose up --build`, which might take a bit of time, like 5-10 min.
If you've already done the build before, simply run
`docker-compose up`

## How to access it as a human user
Go to `localhost:3000` to access stats per season

## How to access it as a non-human user
For example, to create stats use the rest client to call:
POST http://localhost:8080/api/v1/backend/stats/player
`
[
{
"playerId":1,
"season":2023,
"points": 100,
"rebounds":100,
"assists":100,
"steals":100,
"blocks":100,
"fouls":6,
"turnovers": 100,
"minutesPlayed":47
}]
`


## Tech stack choices
* Application is split in two services: backend and frontend, backend being in Java + Spring Boot, frontend in React.js.
<br>I was considering Thymeleaf as a frontend first, but the requirements were mentioning maintainability and scalability,
where Thymeleaf doesn't shine.
* Relational database choice - PostgreSQL due to its easy setup and me being familiar with it :)
* Docker Compose was chosen for the same reason as the relational database

## System design choices
### Domain and data volumes estimation
* Since domain is clearly defined as NBA stats, there are 30 teams in NBA, 15 players in the team and 82 games 
for each team in the season, which makes it max 36900 possible stats records per season. This system should allow
logging player stats, so each year max of 36900 records need to be added, the most load might be during the import of 
the historical data.

### Synchronous vs Asynchronous 
Requirement states that system should be able to scale to tens or hundreds of concurrent requests, 
which fits into limits of API over HTTP. I think in this case, especially knowing the domain, queue setup and maintenance
is unnecessary. Hence, I decided to standard synchronous REST API. API supports bulk addition of stats to make it more efficient.

### Database/Hibernate
* Schema evolution is achieved by flyway scripts. 
* Current domain is represented by three entities: PlayerStats which contains all the stats by season and references Player,
Player contains basic information about a player and references Team. There is a little flaw in design: each stats row has a season
but there is no game date, which would make uniqueness check possible to enforce that pair of (player_id, game_date) is unique.
* Indices on foreign keys to enhance the joins
* There is a batch processing enabled to have performant addition of stats.
* I was considering materialized view for the improved performance of reading all stats per season but there would be a need
to refresh it every time we insert the data to satisfy requirement of always fresh data served. 
In case of more writes than reads it won't make much sense, but if reads are much more often it makes sense to do later on.

### Scalability
* Here database is a bottleneck and there is no big gain in running many backends. Hikari connection pool size is set to 50
which is approx half of connections count that database can allow. It could make sense to run 2 backend instances 
for the max performance in current setup.
* For further improvement, stats table in database can be enhanced partition by season or game date range (if it will be introduced)

## What is missing?
* Tests for backend + frontend. For backend idea was to write tests using Testcontainers
* API documentation
* Setup of two instances of backend to showcase the scalability 
* Partitions of stats table
* Parametrize proxy url and port in nginx config so that it can be passed from docker compose instead of being hardcoded