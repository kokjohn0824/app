package com.finalpretty.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
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
@Table(name = "member")
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Integer member_id;

	@Column(name = "nickname", columnDefinition = "nvarchar(50)")
	private String nickname;

	@Column(name = "gender")
	private Integer gender;

	@Column(name = "age")
	private Integer age;

	@Column(name = "height")
	private double height;

	@Column(name = "weight")
	private double weight;

	@Column(name = "bodyFat")
	private double bodyFat;

	@Column(name = "visceralFat")
	private double visceralFat;

	@Column(name = "muscleMass")
	private double muscleMass;

	@Lob
	@Column(name = "changePhoto")
	private byte[] changePhoto;

	@Column(name = "becomeVIP")
	private Integer becomeVIP;

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "create_date", columnDefinition = "datetime")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss EEEE", timezone = "GMT+8")
	private Date create_date;

	@PrePersist
	public void onCreate() {
		if (create_date == null) {
			create_date = new Date();
		}
	}

	// 跟使用著一對一的連動
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "fkMember")
	private Users users;

	// 跟文章多對多的連動
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "article_like", joinColumns = {
			@JoinColumn(name = "fk_member_id", referencedColumnName = "member_id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_article_id", referencedColumnName = "article_id") })
	private Set<Article> articles = new HashSet<Article>();

	// 跟影片多對多的連動
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "video_like", joinColumns = {
			@JoinColumn(name = "fk_member_id", referencedColumnName = "member_id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_video_id", referencedColumnName = "video_id") })
	private Set<Video> videos = new HashSet<Video>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "members", cascade = CascadeType.ALL)
	private Set<DailyRecord> daily_records = new HashSet<DailyRecord>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade =
	// CascadeType.ALL)
	// private Set<Post> post = new HashSet<Post>();

	// @OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade =
	// CascadeType.ALL)
	// private Set<Response> response = new HashSet<Response>();

}
