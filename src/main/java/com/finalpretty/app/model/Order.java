package com.finalpretty.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

<<<<<<< HEAD
//TODO:利用lombok 語法 改寫getter, setter 以及 NoArgsConstructor
=======
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

>>>>>>> sharon
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name="order")
public class Order {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer order_id;
	
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
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Order_detail> order_detail = new HashSet<Order_detail>();
	
	
	
	public Order() {
	}




	
	
}
