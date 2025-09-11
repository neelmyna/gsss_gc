package com.mtd.game.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtd.game.entity.Recharge;

@Repository
public interface RechargeRepository extends JpaRepository<Recharge, Integer> {
	List<Recharge> findByMemberId(int memberId);
}
