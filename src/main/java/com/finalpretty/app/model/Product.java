package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
//TODO:利用lombok 語法 改寫getter, setter 以及 NoArgsConstructor
@Entity
@Table(name="product")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;

	@Column(name = "title")
	private String title;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "picture")
	private byte[] picture;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "onsale")
	private Integer onsale;
	
	@OneToOne(mappedBy = "product")
	private Order_detail order_detail;
	
	
	public Product() {
	}





	public Integer getProduct_id() {
		return product_id;
	}





	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}





	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public byte[] getPicture() {
		return picture;
	}


	public void setPicture(byte[] picture) {
		this.picture = picture;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}


	public Integer getOnsale() {
		return onsale;
	}


	public void setOnsale(Integer onsale) {
		this.onsale = onsale;
	}


	public Order_detail getOrder_detail() {
		return order_detail;
	}


	public void setOrder_detail(Order_detail order_detail) {
		this.order_detail = order_detail;
	}
	
}
