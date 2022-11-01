package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDailyDTO {

    private Integer food_daily_id;
    private String foodname;
    private Integer side;
    private Integer title;
}
