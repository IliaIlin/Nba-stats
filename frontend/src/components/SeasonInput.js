import React, { useState } from 'react';
import axios from 'axios';
import PlayerStatsTable from './PlayerStatsTable';
import TeamStatsTable from './TeamStatsTable';

const SeasonInput = () => {
  const [season, setSeason] = useState('');
  const [playerStats, setPlayerStats] = useState(null);
  const [teamStats, setTeamStats] = useState(null);

  const handleSeasonChange = (e) => {
    setSeason(e.target.value);
  };

  const fetchPlayerStats = () => {
    axios.get(`/api/v1/ui/stats/player?season=${season}`)
      .then(response => {
        setPlayerStats(response.data);
        setTeamStats(null);
      })
      .catch(error => {
        console.error('There was an error fetching the player stats!', error);
      });
  };

  const fetchTeamStats = () => {
    axios.get(`/api/v1/ui/stats/team?season=${season}`)
      .then(response => {
        setTeamStats(response.data);
        setPlayerStats(null);
      })
      .catch(error => {
        console.error('There was an error fetching the team stats!', error);
      });
  };

  return (
    <div>
      <h1>Get Stats by Season</h1>
      <input
        type="text"
        value={season}
        onChange={handleSeasonChange}
        placeholder="Enter season (e.g., 2021)"
      />
      <button onClick={fetchPlayerStats}>Get Players Stats</button>
      <button onClick={fetchTeamStats}>Get Teams Stats</button>

      {playerStats && <PlayerStatsTable data={playerStats} />}
      {teamStats && <TeamStatsTable data={teamStats} />}
    </div>
  );
};

export default SeasonInput;