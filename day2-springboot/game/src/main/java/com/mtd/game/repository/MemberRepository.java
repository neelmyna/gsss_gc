package com.mtd.game.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtd.game.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
	Member findByPhone(String phone);
	
}
