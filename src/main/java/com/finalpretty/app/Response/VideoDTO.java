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
public class VideoDTO {
    @JsonProperty("ID")
    private Integer video_id;
    @JsonProperty("標題")
    private String title;
    @JsonProperty("分類")
    private String type;
    @JsonProperty("主要部位")
    private String body_parts;
    @JsonProperty("觀看數")
    private Integer views;
    @JsonProperty("影片")
    private String video;
}
