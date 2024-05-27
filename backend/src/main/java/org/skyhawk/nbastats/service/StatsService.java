package org.skyhawk.nbastats.service;

import org.skyhawk.nbastats.dto.AggregatePlayerStatsDTO;
import org.skyhawk.nbastats.dto.AggregateTeamStatsDTO;
import org.skyhawk.nbastats.dto.PlayerStatsDTO;
import org.skyhawk.nbastats.exception.PlayerStatsCreationForNotExistingPlayerException;
import org.skyhawk.nbastats.model.Player;
import org.skyhawk.nbastats.model.PlayerStats;
import org.skyhawk.nbastats.repository.PlayerRepository;
import org.skyhawk.nbastats.repository.PlayerStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {

    final PlayerStatsRepository playerStatsRepository;
    final PlayerRepository playerRepository;

    @Autowired
    public StatsService(
            PlayerStatsRepository playerStatsRepository,
            PlayerRepository playerRepository
    ) {
        this.playerStatsRepository = playerStatsRepository;
        this.playerRepository = playerRepository;
    }

    public List<AggregatePlayerStatsDTO> getAggregatePlayerStatsBySeason(int season) {
        return playerStatsRepository.findAggregatePlayerStatsBySeason(season);
    }

    public List<AggregateTeamStatsDTO> getAggregateTeamStatsBySeason(int season) {
        return playerStatsRepository.findAggregateTeamStatsBySeason(season);
    }

    @Transactional
    public List<PlayerStatsDTO> createPlayerStats(List<PlayerStatsDTO> playerStatsDTOs) {
        var playerIdsFromDTOs = playerStatsDTOs.stream()
                .map(PlayerStatsDTO::playerId)
                .distinct().toList();
        var existingPlayersInDb = playerRepository.findAllById(playerIdsFromDTOs);
        if (playerIdsFromDTOs.size() != existingPlayersInDb.size()) {
            throw new PlayerStatsCreationForNotExistingPlayerException();
        }
        var entitiesToSave = convertToEntity(playerStatsDTOs, existingPlayersInDb);
        var savedEntities = playerStatsRepository.saveAll(entitiesToSave);
        return convertFromEntity(savedEntities);
    }

    private List<PlayerStats> convertToEntity(List<PlayerStatsDTO> playerStatsDTOs, List<Player> playersFromDb) {
        var mapOfPlayerIdToPlayerEntity = playersFromDb.stream().collect(Collectors.toMap(Player::getId, player -> player));
        return playerStatsDTOs.stream().map(dto ->
                PlayerStats.builder()
                        .player(mapOfPlayerIdToPlayerEntity.get(dto.playerId()))
                        .season(dto.season())
                        .points(dto.points())
                        .rebounds(dto.rebounds())
                        .assists(dto.assists())
                        .steals(dto.steals())
                        .blocks(dto.blocks())
                        .fouls(dto.fouls())
                        .turnovers(dto.turnovers())
                        .minutesPlayed(dto.minutesPlayed())
                        .build()
        ).toList();
    }

    private List<PlayerStatsDTO> convertFromEntity(List<PlayerStats> playerStats) {
        return playerStats.stream().map(entity -> PlayerStatsDTO.builder()
                .playerStatsId(entity.getId())
                .playerId(entity.getPlayer().getId())
                .season(entity.getSeason())
                .points(entity.getPoints())
                .rebounds(entity.getRebounds())
                .assists(entity.getAssists())
                .steals(entity.getSteals())
                .blocks(entity.getBlocks())
                .fouls(entity.getFouls())
                .turnovers(entity.getTurnovers())
                .minutesPlayed(entity.getMinutesPlayed())
                .build()
        ).toList();
    }
}
