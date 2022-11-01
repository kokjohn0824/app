package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DailySportsResponse {
    private String sportsname;
    private Integer time;
    private Integer title;
    private Integer sports_daily_id;
}
