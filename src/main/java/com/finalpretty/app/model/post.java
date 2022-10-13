package com.finalpretty.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Post {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer post_id;

    @Column(name="title")
	private String title;

    @Column(name="text")
	private String text;

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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "article_like", joinColumns = {
            @JoinColumn(name = "fk_article_id", referencedColumnName = "article_id") }, inverseJoinColumns = {
                    @JoinColumn(name = "fk_member_id", referencedColumnName = "member_id") })
    private Set<Member> member = new HashSet<Member>();



}
