package com.finalpretty.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "weight")
	private Integer weight;
	
	@Column(name = "bodyFat")
	private Integer bodyFat;
	
	@Column(name = "drinkingWater")
	private Integer drinkingWater;
	
	@Column(name = "sport_time")
	private Integer sportTime;
	
	@Column(name = "food_side")
	private Integer foodSide;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "date_time", columnDefinition = "datetime")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss EEEE",timezone = "GMT+8")
	private Date date_time;
	
	@ManyToOne
	@JoinColumn(name = "fk_member_id")
	private Member member; 


}
