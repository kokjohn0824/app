package com.finalpretty.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "order_detail") // 訂單內容
public class Order_detail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer order_detail_id;

	@Column(name = "product_name") // 商品名稱
	private String product_name;

	@Column(name = "count") // 商品數量
	private Integer count;

	@Column(name = "total") // 單項商品金額
	private Integer total;

	@ManyToOne
	@JoinColumn(name = "fk_product_id") // 商品ID
	private Product product;

	@ManyToOne
	@JoinColumn(name = "fk_order_id") // 訂單編號
	private Order order;

}