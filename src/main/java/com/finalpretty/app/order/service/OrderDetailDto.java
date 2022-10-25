package com.finalpretty.app.order.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDetailDto {

    private Integer order_detail_id;
    private String product_name;
    private Integer count;
    private Integer total;
    private Integer fk_product_id;
    private Integer fk_order_id;
}
