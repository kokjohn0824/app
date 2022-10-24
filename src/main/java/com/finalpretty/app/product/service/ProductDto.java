package com.finalpretty.app.product.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductDto {

    private Integer product_id;
    private String title;
    private String type;
    private String text;
    private Integer onsale;
    private Integer volume; // 產品銷售量
    private Integer stock;
    private Integer price;
}
