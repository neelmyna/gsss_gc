package com.mtd.game.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.game.entity.Recharge;
import com.mtd.game.repository.RechargeRepository;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/collection")
public class CollectionController {

    @Autowired
    private RechargeRepository rechargeRepo;

    @Tag(name = "Collection by Date", description = "filter collections with date")
    @GetMapping("/{date}")
    public List<Map<String, Object>> getCollection(@PathVariable String date) {
        LocalDate d = LocalDate.parse(date);
        List<Map<String, Object>> resp = new ArrayList<>();

        for (Recharge r : rechargeRepo.findAll()) {
            if (r.getDateTime().toLocalDate().equals(d)) {
                Map<String, Object> row = new HashMap<>();
                row.put("transaction_id", r.getId());
                row.put("member", r.getMember().getName());
                row.put("recharge_amount", r.getAmount());
                resp.add(row);
            }
        }
        return resp;
    }
}
