package com.mtd.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtd.game.entity.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{

}
