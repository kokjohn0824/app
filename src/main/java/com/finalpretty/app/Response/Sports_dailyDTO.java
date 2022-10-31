package com.finalpretty.app.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sports_dailyDTO {

    @JsonProperty("daily_record_id")
    private Integer daily_record_id;
    @JsonProperty("sportsname")
    private String sportsname;
    @JsonProperty("time")
    private Integer time;
}
