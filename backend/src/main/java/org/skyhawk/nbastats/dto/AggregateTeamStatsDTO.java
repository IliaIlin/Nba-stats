package org.skyhawk.nbastats.dto;

public record AggregateTeamStatsDTO(
        String teamName,
        Double avgPoints,
        Double avgRebounds,
        Double avgAssists,
        Double avgSteals,
        Double avgBlocks,
        Double avgFouls,
        Double avgTurnovers,
        Double avgMinutesPlayed
) {
}
