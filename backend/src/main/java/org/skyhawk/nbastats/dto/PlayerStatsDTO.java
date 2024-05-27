package org.skyhawk.nbastats.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record PlayerStatsDTO(

        Long playerStatsId,

        @NotNull
        Long playerId,

        @NotNull
        @Min(1949)
        Integer season,

        @Min(0)
        Integer points,

        @Min(0)
        Integer rebounds,

        @Min(0)
        Integer assists,

        @Min(0)
        Integer steals,

        @Min(0)
        Integer blocks,

        @Min(0)
        @Max(6)
        Integer fouls,

        @Min(0)
        Integer turnovers,

        @Min(0)
        @Max(48)
        Float minutesPlayed
) {
}
