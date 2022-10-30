package com.finalpretty.app.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DailyRecordDto {

    private Integer daily_record_id;
    // 當日體重
    private Integer weight;

    // 當日體脂
    private Integer bodyFat;

    // 當日飲水量
    private Integer drinkingWater;

    // 日期
    private String date_time;

    // 會員身高
    private double height;

    // BMI計算
    private double bmi;
}
