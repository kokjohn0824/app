package com.finalpretty.app.model;

import java.util.Date;
import java.util.HashSet;
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
@Table(name = "daily_record")
public class DailyRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "daily_record_id")
	private Integer daily_record_id;

	// 當日體重
	@Column(name = "weight")
	private Integer weight;

	// 當日體脂
	@Column(name = "bodyFat")
	private Integer bodyFat;

	// 當日飲水量
	@Column(name = "drinkingWater")
	private Integer drinkingWater;

	// 日期
	@Column(name = "date_time")
	private String date_time;

	// 跟Member連動
	@ManyToOne
	@JoinColumn(name = "fk_member_id")
	private Member members;

	// 食物明細外來鍵
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daily_record", cascade = CascadeType.ALL)
	private Set<Food_daily> food_daily;

	// 運動明細外來鍵
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "daily_record", cascade = CascadeType.ALL)
	private Set<Sports_daily> sports_daily = new HashSet<Sports_daily>();

}
