package com.mtd.game.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.game.entity.Collection;
import com.mtd.game.entity.Member;
import com.mtd.game.entity.Recharge;
import com.mtd.game.repository.CollectionRepository;
import com.mtd.game.repository.MemberRepository;
import com.mtd.game.repository.RechargeRepository;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    
	@Autowired
	private RechargeRepository rechargeRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private CollectionRepository collectionRepository;
	
	@PostMapping
	public Recharge createRecharge(@RequestBody Map<String, Object> payload) {
		Integer memberId = (Integer) payload.get("member_id");
	    Number amountNum = (Number) payload.get("amount");
	    
	    if (memberId == null || amountNum == null) {
	        throw new RuntimeException("member_id and amount are required");
	    }
	    
	    float amount = amountNum.floatValue();
	    
	 // Find member
	    Member member = memberRepository.findById(memberId).
	    		orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));
	    
	 // Update member balance
	    float newBalance = member.getBalance() + amount;
	    member.setBalance(newBalance);
	    memberRepository.save(member);
	    
	 // Save recharge
	    Recharge recharge = new Recharge();
	    recharge.setMember(member);
	    recharge.setAmount(amount);
	    recharge.setDateTime(LocalDateTime.now());
	    rechargeRepository.save(recharge);
	    
	 // Update collection
	    LocalDate today = LocalDate.now();
	    Optional<Collection> collectionOptional = collectionRepository.findByDate(today);
		
	    if (collectionOptional.isPresent()) {
	        Collection collection = collectionOptional.get();
	        collection.setAmount(collection.getAmount() + amount);
	        collectionRepository.save(collection);
	    } else {
	        Collection collection = new Collection();
	        collection.setDate(today);
	        collection.setAmount(amount);
	        collectionRepository.save(collection);
	    }
	    
	    return recharge;
		
	}
}
