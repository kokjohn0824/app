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
public class ArticleDTO {
    @JsonProperty("ID")
    private Integer article_id;
    @JsonProperty("標題")
    private String title;
    @JsonProperty("內文")
    private String text;
    @JsonProperty("日期")
    private Date added;
    @JsonProperty("觀看數")
    private Integer views;

    @JsonProperty("影片分類")
    private String type;
}
