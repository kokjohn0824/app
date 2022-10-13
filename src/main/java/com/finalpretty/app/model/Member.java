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
	
	
}