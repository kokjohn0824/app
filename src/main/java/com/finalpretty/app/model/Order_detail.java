package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//TODO: fix Order detail
//@Entity
@Table(name="order_detail")
public class Order_detail {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "count")
	private Integer count;
	
	@OneToOne
	@Column(name = "fk_product_id")
	private Product product;
	
	
//	@ManyToOne
//	@Column(name = "fk_order_id")
//	private Order order;
	
	
	
	public Order_detail() {
	
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public Product getProduct() {
		return product;
	}



	public void setProduct(Product product) {
		this.product = product;
	}



	public Order getOrder() {
		return order;
	}



	public void setOrder(Order order) {
		this.order = order;
	}
	
	
	
	

	

	
	
	
	
	
}
