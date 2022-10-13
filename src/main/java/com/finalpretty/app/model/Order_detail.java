package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="order_detail")
public class Order_detail {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer order_detail_id;
	

	@Column(name = "count")
	private Integer count;
	
	@OneToOne
	@JoinColumn(name = "fk_product_id")
	private Product product;
	
	
	@ManyToOne
	@JoinColumn(name = "fk_order_id")
	private Order order;

	
		
}