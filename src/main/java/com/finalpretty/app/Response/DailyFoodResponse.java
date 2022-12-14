package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class DailyFoodResponse {
    private String foodname;
    private Integer side;
    private Integer title;
    private Integer food_daily_id;
}
