package com.finalpretty.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "daily_record")
public class DailyRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Column(name = "bodyFat")
	private Integer bodyFat;
	
	@Column(name = "drinkingWater")
	private Integer drinkingWater;
	
	@Column(name = "sport_time")
	private Integer sport_time;
	
	@Column(name = "food_side")
	private Integer food_side;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "date_time", columnDefinition = "datetime")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss EEEE",timezone = "GMT+8")
	private Date date_time;
	
	@Column(name = "id")
	private Set<Member> member = new HashSet<Member>(); 

	public DailyRecord() {
	}

}
