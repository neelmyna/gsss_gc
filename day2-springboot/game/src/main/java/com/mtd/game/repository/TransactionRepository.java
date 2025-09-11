package com.mtd.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtd.game.entity.Transactions;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Integer>{
	List<Transactions> findByMemberId(int memberId);
}
