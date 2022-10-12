package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="product")
public class Product {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	
	public Product() {
		
	}
	
	public Product(Integer id, String title, String type, String text, byte[] picture, Integer price, Integer onsale) {
		super();
		this.id = id;
		this.title = title;
		this.type = type;
		this.text = text;
		this.picture = picture;
		this.price = price;
		this.onsale = onsale;
	}

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

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	
	
	
}
