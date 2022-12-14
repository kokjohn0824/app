package com.finalpretty.app.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "video")
public class Video {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id")
	private Integer video_id;

	// 影片標題
	@Column(name = "title")
	private String title;

	// 影片路徑(檔名)
	@Column(name = "url")
	private String url;

	// 影片分類
	@Column(name = "type")
	private String type;

	// 訓練部位
	@Column(name = "body_parts")
	private String body_parts;

	// 觀看數
	@Column(name = "views")
	private Integer views;

	// 影片預覽圖
	@Lob
	@Column(name = "picture")
	private byte[] picture;

	// 跟會員多對多的連動
	@ManyToMany(mappedBy = "videos")
	private Set<Member> members = new HashSet<Member>();

}
