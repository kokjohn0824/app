package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
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
	
	
	
	
}
