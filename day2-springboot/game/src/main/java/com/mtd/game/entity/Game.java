package com.mtd.game.entity;

import jakarta.persistence.*;

@Entity
@Table(name="games")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;
   private String name;
   private String description;
   private double price;
   
   public Game() {}
   public Game(String name, String description, double price) {
	super();
	this.name = name;
	this.description = description;
	this.price = price;
}
   public int getId() {
		return id;
	   }
	   public void setId(int id) {
		this.id = id;
	   }
	   public String getName() {
		return name;
	   }
	   public void setName(String name) {
		this.name = name;
	   }
	   public String getDescription() {
		return description;
	   }
	   public void setDescription(String description) {
		this.description = description;
	   }
	   public double getPrice() {
		return price;
	   }
	   public void setPrice(double price) {
		this.price = price;
	   }
}
