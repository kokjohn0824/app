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

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "article")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "article_id")
	private Integer article_id;

	// 文章標題
	@Column(name = "title")
	private String title;

	// 文章內文
	@Column(name = "text", columnDefinition = "ntext")
	// @Type(type = "org.hibernate.type.StringNVarcharType")
	private String text;

	// 標題圖片
	@Lob
	@Column(name = "picture")
	private byte[] picture;

	// 文章時間
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

	// 跟會員多對多的連動
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "article_like", joinColumns = {
			@JoinColumn(name = "fk_article_id", referencedColumnName = "article_id") }, inverseJoinColumns = {
					@JoinColumn(name = "fk_member_id", referencedColumnName = "member_id") })
	private Set<Member> member = new HashSet<Member>();

}
