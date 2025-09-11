package com.mtd.game.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name="transactions")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Double amount;
	private LocalDateTime dateTime = LocalDateTime.now();

	@ManyToOne
	@JoinColumn(name="member_id")
	@JsonIgnore
	private Member member;

	@ManyToOne
	@JoinColumn(name="game_id")
	private Game game;

	public Transactions() {}
	public Transactions(Double amount, LocalDateTime dateTime, Member member, Game game) {
		super();
		this.amount = amount;
		this.dateTime = dateTime;
		this.member = member;
		this.game = game;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	
	
}
