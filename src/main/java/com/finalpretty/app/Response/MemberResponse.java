package com.finalpretty.app.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MemberResponse {

    private Integer member_id; // 會員ID

    private String nickname; // 暱稱

    private Integer gender; // 性別

    private Integer age; // 年齡

    private double height; // 身高

    private double weight; // 體重

    private double bodyFat; // 體脂

    private double visceralFat; // 內臟脂肪

    private double muscleMass; // 肌肉量

    private Integer becomeVIP; // VIP 可改

}
