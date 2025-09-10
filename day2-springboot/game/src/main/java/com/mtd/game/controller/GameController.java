package com.mtd.game.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mtd.game.entity.Game;
import com.mtd.game.entity.Member;
import com.mtd.game.entity.Transactions;
import com.mtd.game.repository.GameRepository;
import com.mtd.game.repository.MemberRepository;
import com.mtd.game.repository.TransactionRepository;

import jakarta.transaction.Transactional;

@RestController
public class GameController {
	@Autowired
	private GameRepository gameRepo;
	@Autowired
	private MemberRepository memberRepo;
	@Autowired
	private TransactionRepository txRepo;

	@PostMapping("/game")
	public Game addGame(@RequestBody Game game) {
		return gameRepo.save(game);
	}

	@PutMapping("/game/edit/{id}")
	public String editGame(@PathVariable int id, @RequestBody Game game) {
		Game existinggame = gameRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Game not found with ID: " + id));
		if (existinggame != null) {
			existinggame.setName(game.getName());
			existinggame.setPrice(game.getPrice());
			existinggame.setDescription(game.getDescription());
			gameRepo.save(existinggame);
			return "Game Edited Successfully";
		}
		return "Failed to update !";
		
	}
	@DeleteMapping("/game/delete/{id}")
	public String deleteGame(@PathVariable int id) {
		Game existinggame = gameRepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Game not found with ID: " + id));
		if(existinggame != null) {
			gameRepo.deleteById(id);
			return "Game Deleted !";
		}
		return "Failed to Delete Game !";
	}
	
	@GetMapping("/games/all")
	public List<Game> allGames() {
		return gameRepo.findAll();
	}

	@PostMapping("/play")
	@Transactional
	public String play(@RequestBody Map<String, Integer> payload) {
		Integer memberId = payload.get("member_id");
		Integer gameId = payload.get("game_id");

		Member member = memberRepo.findById(memberId)
				.orElseThrow(() -> new RuntimeException("Member not found with ID: " + memberId));
		Game game = gameRepo.findById(gameId)
				.orElseThrow(() -> new RuntimeException("Game not found with ID: " + gameId));

		member.setBalance((float) (member.getBalance() - game.getPrice()));
		memberRepo.save(member);

		Transactions tx = new Transactions();
		tx.setMember(member);
		tx.setGame(game);
		tx.setAmount(game.getPrice());
		txRepo.save(tx);

		return "Game played successfully!";
	}
}
