package com.finalpretty.app.Response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private String nickname; // 訂單人
    private Integer order_id; // ID
    private Long order_num; // 訂單編號
    private Integer total; // 訂單總金額
    private Integer ship; // 運送方式 1..自取 2..宅配
    private Integer paid; // 支付方式 1..現金 2..信用卡
    private String phone; // 電話號碼
    private Integer payment; // 付款狀態
    private String address; // 地址
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date create_date; // 訂單成立時間
    private Integer fk_member_id; // 會員外來鍵

}
