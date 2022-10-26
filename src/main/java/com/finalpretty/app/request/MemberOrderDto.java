package com.finalpretty.app.request;

import java.util.List;

import com.finalpretty.app.Response.OrderDetailDto;
import com.finalpretty.app.Response.OrderDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberOrderDto {
    private OrderDto orderDto;
    private List<OrderDetailDto> detailDto;

}
