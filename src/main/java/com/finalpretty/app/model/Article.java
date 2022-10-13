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
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="article")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="title")
	private String title;
	
	@Column(name="text")
	private String text;
	
	@Lob
	@Column(name="picture")
	private byte[] picture;
	
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	@Column(name = "create_date", columnDefinition = "datetime")
	@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss EEEE",timezone = "GMT+8")
	private Date create_date;
	
	@PrePersist
	public void onCreate() {
		if(create_date == null) {
			create_date = new Date();
		}
	}
	

	//FIXME: Article 需要有連結到member嗎？
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "article_like", joinColumns = {
            @JoinColumn(name = "fk_article_id", referencedColumnName = "id") }, inverseJoinColumns = {
                    @JoinColumn(name = "fk_member_id", referencedColumnName = "id") })
    private Set<Member> member = new HashSet<Member>();

	public Article() {
	}
	
	public Article(Integer id, String title, String text, byte[] picture, Date create_date) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.picture = picture;
		this.create_date = create_date;
	}
	
//	public Article(Integer id, String title, String text, byte[] picture, Date create_date, Set<Member> member) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.text = text;
//		this.picture = picture;
//		this.create_date = create_date;
//		this.member = member;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	
	
}
