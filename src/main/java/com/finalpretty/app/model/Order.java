package com.finalpretty.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "total")
	private Integer total;
	
	@Column(name = "ship")
	private Integer ship;
	
	@Column(name = "paid")
	private Integer paid;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "address")
	private String address;

	
	
	
	@ManyToOne
	@JoinColumn(name = "fk_member_id")
	private Member member;
	
	
	
	
	
	
	
	
	public Order() {
		
	}
	
	
	
	
	

	public Order(Integer id, Integer total, Integer ship, Integer paid, Integer price, String address,
			Integer fk_member_id) {
		super();
		this.id = id;
		this.total = total;
		this.ship = ship;
		this.paid = paid;
		this.price = price;
		this.address = address;
		this.fk_member_id = fk_member_id;
	}
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getShip() {
		return ship;
	}

	public void setShip(Integer ship) {
		this.ship = ship;
	}

	public Integer getPaid() {
		return paid;
	}

	public void setPaid(Integer paid) {
		this.paid = paid;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	
	
}
