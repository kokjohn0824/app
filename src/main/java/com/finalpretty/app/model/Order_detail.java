package com.finalpretty.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="order_detail")
public class Order_detail {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	

	@Column(name = "count")
	private Integer count;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
	@Column(name = "fk_product_id")
	private Integer fk_product_id;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH }, optional = true)
	@Column(name = "fk_order_id")
	private Integer fk_order_id;
	
	
	
	public Order_detail() {
	
	}
	
	
	
	

	public Order_detail(Integer id, Integer count, Integer fk_product_id, Integer fk_order_id) {
		super();
		this.id = id;
		this.count = count;
		this.fk_product_id = fk_product_id;
		this.fk_order_id = fk_order_id;
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

	public Integer getFk_product_id() {
		return fk_product_id;
	}

	public void setFk_product_id(Integer fk_product_id) {
		this.fk_product_id = fk_product_id;
	}

	public Integer getFk_order_id() {
		return fk_order_id;
	}

	public void setFk_order_id(Integer fk_order_id) {
		this.fk_order_id = fk_order_id;
	}
	
	
	
	
	
	
}
