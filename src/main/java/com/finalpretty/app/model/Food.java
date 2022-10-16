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

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "food")
public class Food {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")//FIXME:修改此id的名稱為food id 並檢查其相關關聯性衝突
	private Integer id;
	
	@Column(name = "foodname")
	private String foodname;
	
	@Column(name = "calorie")
	private Integer calorie;
	
	@ManyToOne
	@JoinColumn(name = "fk_dailyrecord_id")
	private DailyRecord daily_record;
	
}
