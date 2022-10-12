package com.finalpretty.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="title")
	private String title;
	
	@Column(name="url")
	private String url;
	
	@Column(name="type")
	private String type;
	
	@Column(name="body_parts")
	private String body_parts;
	
	@Lob
	@Column(name="picture")
	private byte[] picture;
	
//	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "video_like", joinColumns = {
//            @JoinColumn(name = "fk_video_id", referencedColumnName = "id") }, inverseJoinColumns = {
//                    @JoinColumn(name = "fk_member_id", referencedColumnName = "id") })
//	private Set<Member> member = new HashSet<Member>();

	public Video() {
	}
	
	public Video(Integer id, String title, String url, String type, String body_parts, byte[] picture) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.type = type;
		this.body_parts = body_parts;
		this.picture = picture;
	}
	
//	public Video(Integer id, String title, String url, String type, String body_parts, byte[] picture, Set<Member> member) {
//		super();
//		this.id = id;
//		this.title = title;
//		this.url = url;
//		this.type = type;
//		this.body_parts = body_parts;
//		this.picture = picture;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBody_parts() {
		return body_parts;
	}

	public void setBody_parts(String body_parts) {
		this.body_parts = body_parts;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	
	
}
