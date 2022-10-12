package com.finalpretty.app.model;

import java.util.ArrayList;
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
import javax.persistence.Table;

@Entity
@Table(name = "member")
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "age")
	private Integer age;
	
	@Column(name = "height")
	private float height;
	
	@Column(name = "weight")
	private float weight;
	
	@Column(name = "bodyFat")
	private float bodyFat;
	
	@Column(name = "visceralFat")
	private float visceralFat;
	
	@Column(name = "muscleMass")
	private float muscleMass;
	
	@Lob
	@Column(name = "changePhoto")
	private byte[] changePhoto;
	
	@Column(name = "becomeVIP")
	private Integer becomeVIP;
	
//	@OneToOne(cascade = CascadeType.ALL)
//	private Set<Users> users = new HashSet<Users>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "video_like", joinColumns = {
            @JoinColumn(name = "fk_member_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "fk_video_id", referencedColumnName = "id") })
	private Set<Video> video = new HashSet<Video>();
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "article_like", joinColumns = {
            @JoinColumn(name = "fk_member_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "fk_article_id", referencedColumnName = "id") })
	private Set<Article> article = new HashSet<Article>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	private Set<DailyRecord> daily_record = new HashSet<DailyRecord>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
	private List<Order> order = new ArrayList<>();
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
//	private Set<Post> post = new HashSet<Post>();
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "member", cascade = CascadeType.ALL)
//	private Set<Response> response = new HashSet<Response>();
	

	public Member() {
	}

	public Member(Integer age, float height, float weight, float bodyFat, float visceralFat, float muscleMass,
			byte[] changePhoto, Integer becomeVIP, Set<Video> video, Set<Article> article,
			Set<DailyRecord> daily_record) {
		this.age = age;
		this.height = height;
		this.weight = weight;
		this.bodyFat = bodyFat;
		this.visceralFat = visceralFat;
		this.muscleMass = muscleMass;
		this.changePhoto = changePhoto;
		this.becomeVIP = becomeVIP;
		this.video = video;
		this.article = article;
		this.daily_record = daily_record;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getBodyFat() {
		return bodyFat;
	}

	public void setBodyFat(float bodyFat) {
		this.bodyFat = bodyFat;
	}

	public float getVisceralFat() {
		return visceralFat;
	}

	public void setVisceralFat(float visceralFat) {
		this.visceralFat = visceralFat;
	}

	public float getMuscleMass() {
		return muscleMass;
	}

	public void setMuscleMass(float muscleMass) {
		this.muscleMass = muscleMass;
	}

	public byte[] getChangePhoto() {
		return changePhoto;
	}

	public void setChangePhoto(byte[] changePhoto) {
		this.changePhoto = changePhoto;
	}

	public Integer getBecomeVIP() {
		return becomeVIP;
	}

	public void setBecomeVIP(Integer becomeVIP) {
		this.becomeVIP = becomeVIP;
	}

	public Set<Video> getVideo() {
		return video;
	}

	public void setVideo(Set<Video> video) {
		this.video = video;
	}

	public Set<Article> getArticle() {
		return article;
	}

	public void setArticle(Set<Article> article) {
		this.article = article;
	}

	public Set<DailyRecord> getDaily_record() {
		return daily_record;
	}

	public void setDaily_record(Set<DailyRecord> daily_record) {
		this.daily_record = daily_record;
	}

	
}
