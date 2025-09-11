package com.mtd.game.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	@Column(unique = true, nullable = false)
	private String phone;
	@Column(columnDefinition = "FLOAT DEFAULT 0")
	private float balance;
	
	@OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
	private List<Recharge> recharges;
	


	
    public Member() {}
	public Member(String name, String phone, float balance) {
		super();
		this.name = name;
		this.phone = phone;
		this.balance = balance;
		
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public List<Recharge> getRecharges() {
		return recharges;
	}
	public void setRecharges(List<Recharge> recharges) {
		this.recharges = recharges;
	}

	
	
}
