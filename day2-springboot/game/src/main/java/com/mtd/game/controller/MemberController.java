package com.mtd.game.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.game.entity.Collection;
import com.mtd.game.entity.Member;
import com.mtd.game.entity.Recharge;
import com.mtd.game.entity.Transactions;
import com.mtd.game.repository.CollectionRepository;
import com.mtd.game.repository.GameRepository;
import com.mtd.game.repository.MemberRepository;
import com.mtd.game.repository.RechargeRepository;
import com.mtd.game.repository.TransactionRepository;

@RestController
public class MemberController {

    @Autowired
    private MemberRepository memberRepo;
    @Autowired
    private RechargeRepository rechargeRepo;
    @Autowired
    private CollectionRepository collectionRepo;
    @Autowired
    private GameRepository gameRepo;
    @Autowired
    private TransactionRepository txRepo;

    @PostMapping("/members")
    public Member addMember(@RequestBody Map<String, Object> params) {
        Member member = new Member();
        member.setName((String) params.get("name"));
        member.setPhone((String) params.get("phone"));
        float fee = ((Number) params.get("fee")).floatValue();
        member.setBalance(fee);
        memberRepo.save(member);

        //Insert to recharge
        Recharge recharge = new Recharge();
        recharge.setAmount(fee);
        recharge.setMember(member);
        rechargeRepo.save(recharge);
        //Insert to collection

        Optional<Collection> collectionOptional = collectionRepo.findByDate(LocalDate.now());
        if (collectionOptional.isPresent()) {
            Collection collection = collectionOptional.get();
            collection.setAmount(collection.getAmount() + fee);
            collectionRepo.save(collection);
        } else {
            Collection collection = new Collection();
            collection.setAmount(fee);
            collection.setDate(LocalDate.now());
            collectionRepo.save(collection);
        }
        return member;
    }

    @PostMapping("/search")
    public Map<String, Object> search(@RequestBody Map<String, String> payload) {
        Member m = memberRepo.findByPhone(payload.get("phone"));
        Map<String, Object> resp = new HashMap<>();
        resp.put("member", m);
        resp.put("recharge_history", rechargeRepo.findByMemberId(m.getId()));
        resp.put("games", gameRepo.findAll());

        List<Map<String, Object>> played = new ArrayList<>();
        for (Transactions tx : txRepo.findByMemberId(m.getId())) {
            Map<String, Object> row = new HashMap<>();
            row.put("id", tx.getId());
            row.put("date_time", tx.getDateTime());
            row.put("game_name", tx.getGame().getName());
            row.put("amount", tx.getAmount());
            played.add(row);
        }
        resp.put("played_history", played);
        return resp;
    }
}
