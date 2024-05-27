import React from 'react';

const PlayerStatsTable = ({ data }) => {
  return (
    <div>
      <h2>Player Stats</h2>
      <table>
        <thead>
          <tr>
            <th>Team name</th>
            <th>Player name</th>
            <th>Avg points</th>
            <th>Avg rebounds</th>
            <th>Avg assists</th>
            <th>Avg steals</th>
            <th>Avg blocks</th>
            <th>Avg fouls</th>
            <th>Avg turnovers</th>
            <th>Avg minutes played</th>
          </tr>
        </thead>
        <tbody>
          {data.map((player, index) => (
            <tr key={index}>
              <td>{player.teamName}</td>
              <td>{player.playerName}</td>
              <td>{player.avgPoints}</td>
              <td>{player.avgRebounds}</td>
              <td>{player.avgAssists}</td>
              <td>{player.avgSteals}</td>
              <td>{player.avgBlocks}</td>
              <td>{player.avgFouls}</td>
              <td>{player.avgTurnovers}</td>
              <td>{player.avgMinutesPlayed}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default PlayerStatsTable;
