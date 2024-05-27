package org.skyhawk.nbastats.repository;

import org.skyhawk.nbastats.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
