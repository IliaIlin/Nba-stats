package org.skyhawk.nbastats.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Generated;
import org.hibernate.generator.EventType;

import java.time.LocalDateTime;

@Entity
@Table(name = "player_stats")
@Builder
@Getter
public class PlayerStats {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_generator")
    @SequenceGenerator(name = "sequence_generator", sequenceName = "player_stats_id_seq", allocationSize = 20)
    @Column(name = "player_stats_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @Column(name = "season", nullable = false)
    private Integer season;

    @Column(name = "points", nullable = false)
    private Integer points;

    @Column(name = "rebounds", nullable = false)
    private Integer rebounds;

    @Column(name = "assists", nullable = false)
    private Integer assists;

    @Column(name = "steals", nullable = false)
    private Integer steals;

    @Column(name = "blocks", nullable = false)
    private Integer blocks;

    @Column(name = "fouls", nullable = false)
    private Integer fouls;

    @Column(name = "turnovers", nullable = false)
    private Integer turnovers;

    @Column(name = "minutes_played", nullable = false)
    private Float minutesPlayed;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Generated(event = EventType.INSERT)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Generated(event = EventType.UPDATE)
    private LocalDateTime updatedAt;

    @Version
    private Long version;
}
