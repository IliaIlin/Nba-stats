import React from 'react';

const TeamStatsTable = ({ data }) => {
  return (
    <div>
      <h2>Team Stats</h2>
      <table>
        <thead>
          <tr>
            <th>Team name</th>
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
          {data.map((team, index) => (
            <tr key={index}>
              <td>{team.teamName}</td>
              <td>{team.avgPoints}</td>
              <td>{team.avgRebounds}</td>
              <td>{team.avgAssists}</td>
              <td>{team.avgSteals}</td>
              <td>{team.avgBlocks}</td>
              <td>{team.avgFouls}</td>
              <td>{team.avgTurnovers}</td>
              <td>{team.avgMinutesPlayed}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default TeamStatsTable;
