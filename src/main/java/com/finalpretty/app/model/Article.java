package com.finalpretty.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	@Column(name = "added", columnDefinition = "datetime")
	@JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
	private Date added;

	@PrePersist
	public void onCreate() {
		if (added == null) {
			added = new Date();
		}
	}

	@ManyToMany(mappedBy = "articles")
	private Set<Member> members = new HashSet<Member>();

}
