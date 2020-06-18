package com.yx.samples.seata.service;

import com.yx.samples.seata.dto.PlaceOrderRequest;

public interface OrderService {

    /**
     * 下单
     *
     * @param placeOrderRequest 订单请求参数
     */
    void placeOrder(PlaceOrderRequest placeOrderRequest);
}