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

//TODO:利用lombok 語法 改寫getter, setter 以及 NoArgsConstructor
@Entity
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
	
	
	public Order_detail() {
	}

	public Integer getId() {
		return order_detail_id;
	}

	public void setId(Integer order_detail_id) {
		this.order_detail_id = order_detail_id;
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
