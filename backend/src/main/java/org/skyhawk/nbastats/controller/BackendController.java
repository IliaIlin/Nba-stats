package org.skyhawk.nbastats.controller;

import jakarta.validation.Valid;
import org.skyhawk.nbastats.dto.PlayerStatsDTO;
import org.skyhawk.nbastats.model.PlayerStats;
import org.skyhawk.nbastats.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/backend")
public class BackendController {

    final StatsService statsService;

    @Autowired
    public BackendController(StatsService statsService) {
        this.statsService = statsService;
    }

    @PostMapping("/stats/player")
    public ResponseEntity<List<PlayerStatsDTO>> createPlayerStats(@Valid @RequestBody List<PlayerStatsDTO> playerStatsDTOs) {
        return ResponseEntity.ok(statsService.createPlayerStats(playerStatsDTOs));
    }

}
