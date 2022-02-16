package com.text.demo.service.base;

import com.text.demo.entity.OrderEntity;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<String,Object> orderList(String payStatus, String playName, Integer tradeId, Integer currentPage, Integer nowPageSize);

    Boolean orderSave(OrderEntity orderEntity);

    Boolean orderEdit(OrderEntity orderEntity);

    Map<String, Object> orderSelect(int id);

    Boolean orderDel(int[] ids);
}
