package com.finalpretty.app.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "sports")
public class Sports {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sports_id")
	private Integer sports_id;

	@Column(name = "sportsname")
	private String sportsname;

	@Column(name = "calorie")
	private Integer calorie;

	// 食物圖片
	@Lob
	@Column(name = "picture")
	private byte[] picture;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sports", cascade = CascadeType.ALL)
	private List<Sports_daily> sports_daily;

}
