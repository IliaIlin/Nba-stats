package org.skyhawk.nbastats.repository;

import org.skyhawk.nbastats.dto.AggregatePlayerStatsDTO;
import org.skyhawk.nbastats.dto.AggregateTeamStatsDTO;
import org.skyhawk.nbastats.model.PlayerStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerStatsRepository extends JpaRepository<PlayerStats, Long> {

    @Query("SELECT new org.skyhawk.nbastats.dto.AggregatePlayerStatsDTO(" +
            "t.name, p.name, aggreg.avgPoints, aggreg.avgRebounds, aggreg.avgAssists, aggreg.avgSteals, " +
            "aggreg.avgBlocks, aggreg.avgFouls, aggreg.avgTurnovers, aggreg.avgMinutesPlayed) " +
            "FROM (" +
            "SELECT ps.player as player, ROUND(AVG(ps.points), 2) as avgPoints, " +
            "ROUND(AVG(ps.rebounds), 2) as avgRebounds, ROUND(AVG(ps.assists), 2) as avgAssists,"+
            "ROUND(AVG(ps.steals), 2) as avgSteals,"+
            "ROUND(AVG(ps.blocks), 2) as avgBlocks, ROUND(AVG(ps.fouls), 2) as avgFouls,"+
            "ROUND(AVG(ps.turnovers), 2) as avgTurnovers, ROUND(AVG(ps.minutesPlayed), 2) as avgMinutesPlayed "+
            "FROM PlayerStats ps " +
            "WHERE ps.season = :season " +
            "GROUP BY ps.player" +
            ") as aggreg " +
            "JOIN Player p ON aggreg.player.id = p.id " +
            "JOIN Team t ON p.team.id = t.id"
    )
    List<AggregatePlayerStatsDTO> findAggregatePlayerStatsBySeason(@Param("season") Integer season);

    @Query("SELECT new org.skyhawk.nbastats.dto.AggregateTeamStatsDTO(" +
            "t.name, ROUND(AVG(ps.points), 2) as avgPoints, " +
            "ROUND(AVG(ps.rebounds), 2) as avgRebounds, ROUND(AVG(ps.assists), 2) as avgAssists,"+
            "ROUND(AVG(ps.steals), 2) as avgSteals,"+
            "ROUND(AVG(ps.blocks), 2) as avgBlocks, ROUND(AVG(ps.fouls), 2) as avgFouls,"+
            "ROUND(AVG(ps.turnovers), 2) as avgTurnovers, ROUND(AVG(ps.minutesPlayed), 2) as avgMinutesPlayed) "+
            "FROM PlayerStats ps " +
            "JOIN Player p ON ps.player.id = p.id " +
            "JOIN Team t ON p.team.id = t.id " +
            "WHERE ps.season = :season " +
            "GROUP BY t.name"
    )
    List<AggregateTeamStatsDTO> findAggregateTeamStatsBySeason(@Param("season") Integer season);
}
