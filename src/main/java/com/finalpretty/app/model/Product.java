package com.finalpretty.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer product_id;

	@Column(name = "title") // 產品名稱
	private String title;

	@Column(name = "type") // 產品種類
	private String type;

	@Column(name = "text") // 產品介紹
	private String text;

	@Lob
	@Column(name = "picture") // 產品照片
	private byte[] picture;

	@Column(name = "volume")
	private Integer volume; // 產品銷售量

	@Column(name = "stock") // 產品庫存
	private Integer stock;

	@Column(name = "price") // 產品價格
	private Integer price;

	@Column(name = "onsale") // 產品上下架
	private Integer onsale;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
	private List<Order_detail> order_detail;

}
