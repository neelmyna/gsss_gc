package com.mtd.game.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mtd.game.entity.Game;

public interface GameRepository extends JpaRepository<Game, Long> {

}
