package org.skyhawk.nbastats.dto;

public record AggregatePlayerStatsDTO(
        String teamName,
        String playerName,
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
