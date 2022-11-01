package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SportsDailyDTO {

    private Integer sports_daily_id;
    private String sportsname;
    private Integer time;
    private Integer title;
}
