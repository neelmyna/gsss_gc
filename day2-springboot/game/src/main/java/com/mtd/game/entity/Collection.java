package com.mtd.game.entity;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name="collections")
public class Collection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float amount;
    private LocalDate date;
    
    public Collection() {}
	public Collection(float amount, LocalDate date) {
		super();
		this.amount = amount;
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
}
