package com.mtd.game.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtd.game.entity.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
	Optional<Collection> findByDate(LocalDate date);
}
