package org.skyhawk.nbastats.controller;

import org.skyhawk.nbastats.dto.AggregatePlayerStatsDTO;
import org.skyhawk.nbastats.dto.AggregateTeamStatsDTO;
import org.skyhawk.nbastats.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/ui")
public class UIController {

    final StatsService statsService;

    @Autowired
    public UIController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/stats/player")
    public List<AggregatePlayerStatsDTO> getPlayerStats(@RequestParam(name = "season", required = false) int season) {
        return statsService.getAggregatePlayerStatsBySeason(season);
    }

    @GetMapping("/stats/team")
    public List<AggregateTeamStatsDTO> getTeamStats(@RequestParam(name = "season", required = false) int season) {
        return statsService.getAggregateTeamStatsBySeason(season);
    }
}
