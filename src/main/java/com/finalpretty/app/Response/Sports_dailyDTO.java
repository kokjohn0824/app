package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sports_dailyDTO {

    private Integer daily_record_id;
    private String sportsname;
    private Integer time;
}
