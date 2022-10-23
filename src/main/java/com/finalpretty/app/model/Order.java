package com.finalpretty.app.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "[order]")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer order_id;

	@Column(name = "order_num") // 訂單編號
	private Long order_num;

	@Column(name = "total") // 訂單總金額
	private Integer total;

	@Column(name = "ship") // 運送方式 1..自取 2..宅配
	private Integer ship;

	@Column(name = "paid") // 支付方式 1..現金 2..信用卡
	private Integer paid;

	@Column(name = "[address]") // 地址
	private String address;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "create_date")
	private Date create_date; // 訂單成立時間

	@ManyToOne
	@JoinColumn(name = "fk_member_id") // 會員外來鍵
	private Member member;

	// 訂單明細外來鍵
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
	private Set<Order_detail> order_detail;

}
