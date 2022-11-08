package com.finalpretty.app.Response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ArticleDTO2 {
    @JsonProperty("article_id")
    private Integer article_id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("text")
    private String text;
    @JsonProperty("added")
    private Date added;
    @JsonProperty("views")
    private Integer views;

    @JsonProperty("type")
    private String type;
}
